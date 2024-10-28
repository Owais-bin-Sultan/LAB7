package StringPemutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PermutationComparison {

    public static void main(String[] args) {
        String input = "abcd"; // Example input
        boolean excludeDuplicates = false;

        // Measure recursive solution time
        long startTimeRecursive = System.nanoTime();
        List<String> recursivePermutations = StringPermutations.generatePermutations(input, excludeDuplicates);
        long endTimeRecursive = System.nanoTime();
        System.out.println("Recursive Permutations Count: " + recursivePermutations.size());
        System.out.println("Time taken (recursive): " + (endTimeRecursive - startTimeRecursive) + " nanoseconds");

        // Measure non-recursive solution time
        long startTimeNonRecursive = System.nanoTime();
        List<String> nonRecursivePermutations = generatePermutations(input, excludeDuplicates);
        long endTimeNonRecursive = System.nanoTime();
        System.out.println("Non-Recursive Permutations Count: " + nonRecursivePermutations.size());
        System.out.println("Time taken (non-recursive): " + (endTimeNonRecursive - startTimeNonRecursive) + " nanoseconds");
    }

    public static List<String> generatePermutations(String str, boolean excludeDuplicates) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result; // Return an empty list for null or empty input
        }

        Stack<String> stack = new Stack<>();
        stack.push("");

        while (!stack.isEmpty()) {
            String prefix = stack.pop();

            if (prefix.length() == str.length()) {
                if (excludeDuplicates) {
                    if (!result.contains(prefix)) {
                        result.add(prefix);
                    }
                } else {
                    result.add(prefix);
                }
            } else {
                for (int i = 0; i < str.length(); i++) {
                    // If the current character is already in the prefix, skip it (for duplicates)
                    if (prefix.indexOf(str.charAt(i)) == -1) {
                        stack.push(prefix + str.charAt(i));
                    }
                }
            }
        }

        return result;
    }
}
