package FileSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileSearch {
    private static boolean caseSensitive = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the directory path
        System.out.print("Directory path: ");
        String directoryPath = scanner.nextLine();

        // Prompt the user to enter multiple file names (comma-separated)
        System.out.print("File names to search for (comma-separated, with extensions): ");
        String fileNamesInput = scanner.nextLine();
        String[] fileNames = fileNamesInput.split(",");

        // Option to set case sensitivity
        System.out.print("Case-sensitive search? (yes/no): ");
        String caseSensitiveInput = scanner.nextLine();
        caseSensitive = caseSensitiveInput.equalsIgnoreCase("yes");

        scanner.close();

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a directory.");
            return;
        }

        Map<String, List<String>> searchResults = new HashMap<>();

        // Perform a search for each file name
        for (String fileName : fileNames) {
            fileName = fileName.trim(); // Remove any extra whitespace
            List<String> foundFiles = searchFiles(directory, fileName);
            searchResults.put(fileName, foundFiles);
        }

        // Display results for each file name
        for (String fileName : searchResults.keySet()) {
            List<String> foundFiles = searchResults.get(fileName);
            if (foundFiles.isEmpty()) {
                System.out.println("File not found: " + fileName);
            } else {
                System.out.println("File(s) found for '" + fileName + "':");
                for (String path : foundFiles) {
                    System.out.println(path);
                }
                System.out.println("Total occurrences of '" + fileName + "': " + foundFiles.size());
            }
        }
    }

    /**
     * Recursively searches for the specified file in the directory and its subdirectories.
     *
     * @param directory the starting directory for the search
     * @param fileName the name of the file to search for
     * @return a list of full paths to the files found
     */
    public static List<String> searchFiles(File directory, String fileName) {
        List<String> foundFiles = new ArrayList<>();

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                foundFiles.addAll(searchFiles(file, fileName)); // Recursive call for subdirectories
            } else {
                if (isFileMatch(file.getName(), fileName)) {
                    foundFiles.add(file.getAbsolutePath());
                }
            }
        }
        return foundFiles;
    }

    /**
     * Checks if a file matches the search criteria based on case sensitivity.
     *
     * @param fileName the name of the file to check
     * @param searchName the name of the file to search for
     * @return true if the file name matches the search criteria, false otherwise
     */
    public static boolean isFileMatch(String fileName, String searchName) {
        if (caseSensitive) {
            return fileName.equals(searchName);
        } else {
            return fileName.equalsIgnoreCase(searchName);
        }
    }
}

