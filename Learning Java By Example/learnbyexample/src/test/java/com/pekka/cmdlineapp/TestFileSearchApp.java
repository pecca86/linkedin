package com.pekka.cmdlineapp;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestFileSearchApp {

    private FileSearchApp fileSearchApp;


    @Before
    public void setUp() {
        fileSearchApp = new FileSearchApp();
    }

    @Test
    public void shouldRemoveBackslashes() {
        String result = fileSearchApp.getRelativeFileName(new File("./"), new File("\\"));
        assertEquals("", result);
    }

    @Test
    public void shouldConvertToUnixPath() {
        String result = fileSearchApp.getRelativeFileName(new File("./src/test/resources/file.txt"), new File("\\dir"));
        assertEquals("c/test/resources/file.txt", result);
    }

    @Test
    public void shouldSetPath() {
        fileSearchApp.setPath("testPath");
        assertEquals("testPath", fileSearchApp.getPath());
    }

    @Test
    public void shouldReturnMatchFromFileSearchWithJava8() throws IOException {
        File file = new File("./src/test/resources/file.txt");
        fileSearchApp.setRegex("dance");
        assertTrue(fileSearchApp.searchFileJava8(file));
    }

    @Test
    public void shouldSetZipFileName() {
        fileSearchApp.setZipFileName("zippedy");
        assertEquals("zippedy", fileSearchApp.getZipFileName());
    }


    @Test
    public void shouldMatchStringInFile() {
        fileSearchApp.setRegex("wa..a");
        assertTrue("Should be a match", fileSearchApp.searchText("wanna"));
    }

    @Test
    public void shouldCreateAZipFile() throws IOException {
        File file = new File("./src/test/resources/file.txt");
        fileSearchApp.setRegex("dance");
        fileSearchApp.setZipFileName("testSuiteZip");
        fileSearchApp.setPath("./");
        fileSearchApp.addFileToZip(file);
        File created = new File("../testSuiteZip.zip");
        System.out.println("PATH: " + created.getAbsolutePath());
        assertTrue(created.exists());
    }

    @Test
    public void shouldAddFileToFileList() throws IOException {
        File file = new File("./src/test/resources/file.txt");
        fileSearchApp.setRegex("dance");
        fileSearchApp.setZipFileName("../testSuiteZip.zip");
        fileSearchApp.setPath("./");
        fileSearchApp.addFileToZip(file);
        assertEquals(1, fileSearchApp.getZipFiles().size());
    }

    @Test
    public void shouldProcessAndNotAddToZipfileList() {
        File file = new File("./src/test/resources/file.txt");
        fileSearchApp.setRegex("AKFOAFKAEOK");
        fileSearchApp.processFile(file);
        assertEquals(0, fileSearchApp.getZipFiles().size());
    }

    @Test
    public void shouldProcessAndAddToZipfileList() {
        File file = new File("./src/test/resources/file.txt");
        fileSearchApp.setRegex("dance");
        fileSearchApp.setZipFileName("testSuiteZip.zip");
        fileSearchApp.setPath("./");
        fileSearchApp.processFile(file);
        assertEquals(1, fileSearchApp.getZipFiles().size());
    }
}
