package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import StringPemutation.StringPermutations;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StringPermutationsTest {

    @Test
    public void testSimplePermutations() {
        String input = "abc";
        boolean excludeDuplicates = false;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);
        
        // Expected permutations
        String[] expected = {"abc", "acb", "bac", "bca", "cab", "cba"};
        assertEquals(expected.length, result.size(), "The number of permutations should be correct.");
        for (String perm : expected) {
            assertTrue(result.contains(perm), "Expected permutation not found: " + perm);
        }
    }

    @Test
    public void testPermutationsWithDuplicates() {
        String input = "aab";
        boolean excludeDuplicates = true;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);

        // Expected unique permutations
        String[] expected = {"aab", "aba", "baa"};
        assertEquals(expected.length, result.size(), "The number of unique permutations should be correct.");
        for (String perm : expected) {
            assertTrue(result.contains(perm), "Expected unique permutation not found: " + perm);
        }
    }

    @Test
    public void testEmptyString() {
        String input = "";
        boolean excludeDuplicates = false;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);

        // Expected: no permutations found
        assertTrue(result.isEmpty(), "Permutations of an empty string should be empty.");
    }

    @Test
    public void testSingleCharacter() {
        String input = "a";
        boolean excludeDuplicates = false;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);

        // Expected: single permutation
        assertEquals(1, result.size(), "There should be one permutation for a single character.");
        assertTrue(result.contains("a"), "The permutation should be 'a'.");
    }

    @Test
    public void testExcludeDuplicatesWithSingleCharacter() {
        String input = "aaa";
        boolean excludeDuplicates = true;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);

        // Expected: no unique permutations
        assertTrue(result.isEmpty(), "There should be no unique permutations for identical characters.");
    }

    @Test
    public void testNumericString() {
        String input = "123";
        boolean excludeDuplicates = false;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);

        // Expected permutations for numeric characters
        String[] expected = {"123", "132", "213", "231", "312", "321"};
        assertEquals(expected.length, result.size(), "The number of permutations should be correct.");
        for (String perm : expected) {
            assertTrue(result.contains(perm), "Expected permutation not found: " + perm);
        }
    }

    @Test
    public void testSpecialCharacters() {
        String input = "a@b";
        boolean excludeDuplicates = false;

        List<String> result = StringPermutations.generatePermutations(input, excludeDuplicates);

        // Expected permutations including special characters
        String[] expected = {"a@b", "ab@", "a@b", "b@a", "ba@", "@ab", "@ba"};
        assertEquals(expected.length, result.size(), "The number of permutations should be correct.");
        for (String perm : expected) {
            assertTrue(result.contains(perm), "Expected permutation not found: " + perm);
        }
    }
}

