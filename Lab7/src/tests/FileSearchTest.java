package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import FileSystem.FileSearch;

import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileSearchTest {
    private static final String TEMP_DIR = "E:/files"; // Base directory for tests

    @BeforeAll
    public static void setUp() throws IOException {
        // Create temporary directories and files for testing
        Files.createDirectories(Paths.get(TEMP_DIR + "/a"));
        Files.createDirectories(Paths.get(TEMP_DIR + "/b"));
        Files.createDirectories(Paths.get(TEMP_DIR + "/c"));

        // Create test files
        Files.createFile(Paths.get(TEMP_DIR + "/a/mok.txt"));
        Files.createFile(Paths.get(TEMP_DIR + "/b/mok.txt"));
        Files.createFile(Paths.get(TEMP_DIR + "/c/mok.txt"));
        Files.createFile(Paths.get(TEMP_DIR + "/a/king1.txt"));
        Files.createFile(Paths.get(TEMP_DIR + "/b/king1.txt"));
    }

    @AfterAll
    public static void tearDown() throws IOException {
        // Delete temporary test directory after all tests
        deleteDirectory(new File(TEMP_DIR));
    }

    // Helper method to delete directories recursively
    private static void deleteDirectory(File dir) throws IOException {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteDirectory(child);
                }
            }
        }
        Files.delete(dir.toPath());
    }

    @Test
    public void testMultipleFilesFoundCaseInsensitive() {
        String directoryPath = TEMP_DIR;
        String[] fileNames = {"mok.txt", "king1.txt"};
        boolean caseSensitive = false;

        // Execute search
        for (String fileName : fileNames) {
            List<String> foundFiles = FileSearch.searchFiles(new File(directoryPath), fileName);
            assertFalse(foundFiles.isEmpty(), "File not found: " + fileName);
        }
    }

    @Test
    public void testFileNotFound() {
        String directoryPath = TEMP_DIR;
        String fileName = "nonexistent.txt";

        // Execute search
        List<String> foundFiles = FileSearch.searchFiles(new File(directoryPath), fileName);
        assertTrue(foundFiles.isEmpty(), "File should not be found: " + fileName);
    }

    @Test
    public void testCaseSensitiveSearch() {
        String directoryPath = TEMP_DIR;
        String fileName = "MOK.TXT"; // Different case

        // Execute search
        List<String> foundFiles = FileSearch.searchFiles(new File(directoryPath), fileName);
        assertTrue(foundFiles.isEmpty(), "File should not be found in case-sensitive search: " + fileName);
    }

    @Test
    public void testMixedResults() {
        String directoryPath = TEMP_DIR;
        String[] fileNames = {"mok.txt", "nonexistent.txt"};

        // Execute search for both
        for (String fileName : fileNames) {
            List<String> foundFiles = FileSearch.searchFiles(new File(directoryPath), fileName);
            if (fileName.equals("mok.txt")) {
                assertFalse(foundFiles.isEmpty(), "File should be found: " + fileName);
            } else {
                assertTrue(foundFiles.isEmpty(), "File should not be found: " + fileName);
            }
        }
    }

    @Test
    public void testEmptyDirectory() throws IOException {
        String emptyDir = TEMP_DIR + "/empty";
        Files.createDirectory(Paths.get(emptyDir)); // Create an empty directory

        String fileName = "file.txt";

        // Execute search
        List<String> foundFiles = FileSearch.searchFiles(new File(emptyDir), fileName);
        assertTrue(foundFiles.isEmpty(), "File should not be found in an empty directory: " + fileName);

        // Cleanup
        deleteDirectory(new File(emptyDir)); // Remove the empty directory
    }
}
