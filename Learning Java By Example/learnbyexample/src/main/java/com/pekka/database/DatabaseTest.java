package com.pekka.database;

import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseTest {
    private static final String DB_PROPERTIES = "db.properties";
    private static final String DB_URL = "dbUrl";
    private static final String DB_USER = "dbUser";
    public static final String DB_PASSWORD = "dbPassword";
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private Properties globalProps;


    public static void main(String[] args) {
        DatabaseTest test = new DatabaseTest();
        // We name the logger, so it can be referred to inside a config file
        Logger logger = Logger.getLogger("com.pekka.database");

        FileHandler handler = null;
        try {
            handler = new FileHandler("mylog.xml");
            logger.addHandler(handler);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error creating log file", e);
        }

        try {
            logger.log(Level.INFO, "About to test the connection");
            test.testConnection();
            logger.log(Level.INFO, "Finished testing the connection");
        } catch (IOException | SQLException e) {
            logger.log(Level.WARNING, "Error testing connection", e);
        }
    }


    public void writeProperties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("dbUrl", "localhost");
        properties.setProperty("dbUser", "pekka");
        properties.setProperty("dbPassword", "password");

        // Write values to file 'project.properties'
        try (OutputStream out = new FileOutputStream("project.properties")) {
            properties.store(out, "Database properties file");
        }
    }

    public void readProperties() throws IOException {
        try (InputStream in = new FileInputStream(DB_PROPERTIES)) {
            globalProps = new Properties();
            globalProps.load(in);
        }
    }

    // One way to connect to the db
    public Connection connectToDatabase() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", globalProps.getProperty(DB_USER));
        connectionProps.put("password", globalProps.getProperty(DB_PASSWORD));

        // Register driver is optional for some drivers, required for others
        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        Connection conn = DriverManager.getConnection(
                "jdbc:derby:test;create=true",
                connectionProps);
        return conn;
    }

    // A better way to connect to the db
    public Connection connectUsingDataSource() throws SQLException {
        EmbeddedDataSource ds = new org.apache.derby.jdbc.EmbeddedDataSource();
        ds.setDatabaseName("test.db;create=true");
        ds.setUser( globalProps.getProperty(DB_USER) );
        ds.setPassword( globalProps.getProperty(DB_PASSWORD) );
        Connection conn = ds.getConnection();

        // paranoid mode for connection pools: make sure autoCommit is on
        conn.setAutoCommit(true);
        return conn;
    }

    public void createDatabaseTable(Connection conn) throws SQLException {
        // Statement implements auto closable
        try (Statement s = conn.createStatement()) {
            s.executeUpdate("CREATE TABLE testdata(" +
                    "num INT, " +
                    "dt TIMESTAMP, " +
                    "txt VARCHAR(256))");
        }
    }

    // Add arbitrary data to the db
    public void addData(Connection conn) throws SQLException {
        String sql = "INSERT INTO testdata VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            Random rnd = new Random();
            long timenow = System.currentTimeMillis();

            for (int i = 0; i < 10; i++) {
                int num = rnd.nextInt(1000);
                long time = timenow - (num ^ 2);

                ps.setInt(1, num);
                ps.setTimestamp(2, new Timestamp(time));
                ps.setString(3, time + ": " + (new Date(time)));
                ps.executeUpdate();
            }
        }
    }


    public void readData(Connection conn) throws SQLException {
        try (Statement query = conn.createStatement()) {
            String sql = "SELECT * FROM testdata";
            query.setFetchSize(100);

            try (ResultSet rs = query.executeQuery(sql)) {
                while (!rs.isClosed() && rs.next()) {
                    int num = rs.getInt(1);
                    Timestamp dt = rs.getTimestamp(2);
                    String txt = rs.getString(3);
                    System.out.println(num + "; " + dt + "; " + txt);
                }
            }

        }
    }


    public void closeDatabaseConnection(Connection conn) throws SQLException {
        conn.close();

        try {
            // we do this because we are accessing a database file directly;
            // if we were connecting to a database server we would never do this
            DriverManager.getConnection("jdbc:derby:test;shutdown=true");
        } catch (SQLException sqe) {
            if ((sqe.getErrorCode() == 45000) || (sqe.getErrorCode() == 50000)) {
                // Ignore these error codes; Derby ALWAYS throws an error when
                // you shutdown a database: 08006 (45000) if it's a single
                // database, XJ015 (50000) if you close all databases
            } else {
                throw sqe;
            }
        }
    }


    public void testConnection() throws IOException, SQLException {
        readProperties();
        Connection conn = connectToDatabase();

        try {
            createDatabaseTable(conn);
            addData(conn);
        } catch (SQLException e) {
            System.out.println("Database table already exists");
        }

        readData(conn);
        closeDatabaseConnection(conn);
    }


    public SecretKey generateNewKey() throws GeneralSecurityException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);		// options are 128, 192, 256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    // Three ways of storing the key
    private SecretKey buildKey() throws GeneralSecurityException {
        // AES key byte arrays must be exactly 16, 24, or 32 bytes in length
        byte[] keyBytes = new byte[16];
        SecretKey secretKey = null;


        // OPTION 1 (from a known, previously generated secretKey.getEncoded())
        keyBytes = new byte[] {-89, -99, 101, -9, 80, 28, 25, -111,
                -121, -40, 14, 53, 54, 2, 53, 24};
        secretKey = new SecretKeySpec(keyBytes, "AES");


        // OPTION 2 (from a known String -- less secure, small pool of byte options)
        try {
            String pwdString = "asdfjkl;asdfjkl;";
            byte[] pwdBytes = pwdString.getBytes("UTF-8");
            System.arraycopy(pwdBytes, 0, keyBytes, 0,
                    Math.min(keyBytes.length, pwdBytes.length));
            secretKey = new SecretKeySpec(keyBytes, "AES");
        } catch (UnsupportedEncodingException e) {
            // ignore, this will never happen so we don't want to
            // force the caller to catch it
        }


        // OPTION 3 (from a known random seed -- DANGER: might vary in different JVMs)
        long secretSeed = 123456789;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(secretSeed);
        keyGenerator.init(128, random);
        secretKey = keyGenerator.generateKey();

        return secretKey;
    }


    private String encryptString(String plainText, SecretKey secretKey)
            throws GeneralSecurityException, IOException {
        // Create a cipher object using the same algorithm as the key
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        // Encrypt using cipher and secrey key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] textBytes = plainText.getBytes("UTF-8");
        byte[] encryptedBytes = cipher.doFinal(textBytes);

        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedBytes);
        return encryptedText;
    }


    // It is best practice to store to plain text password as a char[] instead of a String
    // This is because of how garbage collection works in the JVM, a string stays longer in memory
    private char[] decryptString(String encryptedText, SecretKey secretKey)
            throws GeneralSecurityException, IOException {
        // Decode Base64
        Base64.Decoder decoder = Base64.getDecoder();
        // Create byte array
        byte[] encryptedBytes = decoder.decode(encryptedText);

        // Decrypt byte array to another byte array
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        // Convert byte array to char array
        Charset utf8 = StandardCharsets.UTF_8;
        CharBuffer decryptedChars = utf8.decode(ByteBuffer.wrap(decryptedBytes));
        return decryptedChars.array();
    }


    // Test our encryption - decryption methods
    public void keyTest() {
        try {
            SecretKey secretKey = buildKey();
            String encrypted = encryptString("password", secretKey);
            // Char arrays stay in memory for a shorter time period
            char[] decrypted = decryptString(encrypted, secretKey);
            System.out.println("encrypted password: " + encrypted);
            System.out.println("decrypted password: " + new String(decrypted));
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }


}
