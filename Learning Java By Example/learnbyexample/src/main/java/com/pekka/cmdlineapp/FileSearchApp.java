package com.pekka.cmdlineapp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileSearchApp {
    private String path;
    private String regex;
    private String zipFileName;
    private Pattern pattern;
    private List<File> zipFiles = new ArrayList<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    public List<File> getZipFiles() {
        return zipFiles;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public static void main(String[] args) {
        FileSearchApp fileSearchApp = new FileSearchApp();

        // Take max three args in to account
        switch ( Math.min(args.length, 3)) {
            case 0:
                System.out.println("Args: path, regex, zipfile name");
                return;
            case 3: fileSearchApp.setZipFileName(args[2]);
            case 2: fileSearchApp.setRegex(args[1]);
            case 1: fileSearchApp.setPath(args[0]);
        }

        // Traverse the directory
        try {
            fileSearchApp.walkDirectoryJava8(fileSearchApp.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void walkDirectoryJava6(String path) throws IOException {

        File dir = new File(path);
        File[] files = dir.listFiles();
        
        for (File f : files) {
            if (f.isDirectory()) {
                walkDirectoryJava6(f.getAbsolutePath());
            } else {
                processFile(f);
            }
        }
    }

    private void walkDirectoryJava7(String path) throws IOException {
        System.out.println("Walking fs using Java 7");

        // Processes all directories AND subdirectories!
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
           @Override
           public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws  IOException {
               processFile(file.toFile());
               return FileVisitResult.CONTINUE;
           }
        });
    }

    private void walkDirectoryJava8(String path) throws IOException {
        System.out.println("Walking fs using Java 8");

        Files.walk(Paths.get(path))
                .forEach(f -> processFile(f.toFile()));
    }

    public void processFile(File f) {
        System.out.println("=> Processing: " + f);
        try {
            if (fileContentMatchesRegex(f)) {
                addFileToZip(f);
            }
        } catch (IOException | UncheckedIOException e) {
            System.out.println("!! Error processing: " + f + ": " + e);
        }
    }

    private boolean fileContentMatchesRegex(File f) throws IOException, UncheckedIOException {
        return searchFileJava8(f);
    }

    public void addFileToZip(File file) throws IOException {
        if (getZipFileName() != null) {
            System.out.println("Adding " + file + " to zip file...");
            zipFiles.add(file);
        }

        //zipFilesJava6();
        zipFilesJava7();
    }

    private void zipFilesJava6() throws IOException {
        ZipOutputStream out = null;

        try {
            out = new ZipOutputStream(new FileOutputStream(getZipFileName()));
            File baseDir = new File(getPath());

            for (File f : zipFiles) {
                String fileName = getRelativeFileName(f, baseDir);

                ZipEntry zipEntry = new ZipEntry(fileName);
                out.putNextEntry(zipEntry);

                int bufferSize = 2048;
                byte[] buffer = new byte[bufferSize];
                int len = 0;
                BufferedInputStream in = new BufferedInputStream(
                        new FileInputStream(f), bufferSize);
                while ((len = in.read(buffer, 0, bufferSize)) != -1) {
                    out.write(buffer, 0, len);
                }
                in.close();
                out.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public String getRelativeFileName(File f, File baseDir) {
        String fileName = f.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        // Zip entry file name must use "/" and not "\"
        fileName.replace('\\', '/');
        while (fileName.startsWith("/")) {
            fileName = fileName.substring(1);
        }
        return fileName;
    }

    public void zipFilesJava7() throws IOException {
        try (ZipOutputStream out =
                new ZipOutputStream(new FileOutputStream(getZipFileName())) ) {
            File baseDir = new File(getPath());

            for (File file : zipFiles) {
                String fileName = getRelativeFileName(file, baseDir);

                ZipEntry zipEntry = new ZipEntry(fileName);
                zipEntry.setTime(file.lastModified());
                out.putNextEntry(zipEntry);

                Files.copy(file.toPath(), out); // Using this we don't need the file buffer like in java6

                out.closeEntry();
            }
        }
    }

    /**
     * Search file content if matching regex is found.
     * @param file text file which contents we want to read
     * @return true if regex is found
     * @throws FileNotFoundException
     */
    private boolean searchFileJava6(File file) throws FileNotFoundException {
        boolean found = false;
        Scanner scanner = new Scanner(file, "UTF-8");
        while (scanner.hasNextLine()) {
            found = searchText(scanner.nextLine());
            if (found) break;
        }
        scanner.close();
        return found;
    }

    private boolean searchFileJava7(File file) throws IOException {
        // Reads the whole file in to memory, so not a good choice for large files
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        for (String s : lines) {
            if (searchText(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchFileJava8(File file) throws IOException {
        return Files.lines(file.toPath(), StandardCharsets.UTF_8)
                .anyMatch(f -> searchText(f));
    }

    public boolean searchText(String searchText) {
        return (this.getRegex() == null) ? false :
            //this.pattern.matcher(searchText).matches(); // FULL MATCH
            this.pattern.matcher(searchText).find(); // PARTIAL MATCH
    }
}
