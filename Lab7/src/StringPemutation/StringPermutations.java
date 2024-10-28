package StringPemutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class StringPermutations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Option to exclude duplicates
        System.out.print("Exclude duplicate permutations? (yes/no): ");
        String excludeDuplicatesInput = scanner.nextLine();
        boolean excludeDuplicates = excludeDuplicatesInput.equalsIgnoreCase("yes");

        // Generate permutations
        List<String> permutations = generatePermutations(input, excludeDuplicates);

        // Display the results
        if (permutations.isEmpty()) {
            System.out.println("No permutations found.");
        } else {
            System.out.println("Permutations:");
            for (String perm : permutations) {
                System.out.println(perm);
            }
        }

        scanner.close();
    }

    /**
     * Generates all permutations of the given string using recursion.
     *
     * @param str The input string
     * @param excludeDuplicates Whether to exclude duplicate permutations
     * @return A list of all unique permutations
     */
    public static List<String> generatePermutations(String str, boolean excludeDuplicates) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result; // Return an empty list for null or empty input
        }
        generatePermutationsHelper("", str, result, excludeDuplicates);
        return result;
    }

    /**
     * Helper method to recursively generate permutations.
     *
     * @param prefix Current prefix of the permutation
     * @param remaining Remaining characters to permute
     * @param result List to store the permutations
     * @param excludeDuplicates Whether to exclude duplicate permutations
     */
    private static void generatePermutationsHelper(String prefix, String remaining, List<String> result, boolean excludeDuplicates) {
        if (remaining.isEmpty()) {
            if (excludeDuplicates) {
                if (!result.contains(prefix)) {
                    result.add(prefix);
                }
            } else {
                result.add(prefix);
            }
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                char currentChar = remaining.charAt(i);
                // Create a new string excluding the current character
                String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
                generatePermutationsHelper(prefix + currentChar, newRemaining, result, excludeDuplicates);
            }
        }
    }
}
