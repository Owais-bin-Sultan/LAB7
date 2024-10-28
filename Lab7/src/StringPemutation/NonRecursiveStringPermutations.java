package StringPemutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NonRecursiveStringPermutations {

    public static void main(String[] args) {
        String input = "abc"; // Example input
        boolean excludeDuplicates = false; // Example setting

        long startTime = System.nanoTime();
        List<String> permutations = generatePermutations(input, excludeDuplicates);
        long endTime = System.nanoTime();

        System.out.println("Permutations (non-recursive):");
        for (String perm : permutations) {
            System.out.println(perm);
        }

        System.out.println("Time taken (non-recursive): " + (endTime - startTime) + " nanoseconds");
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

