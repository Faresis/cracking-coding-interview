import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;

class PalindromePermutationString {
  /**
   * Map based solution.
   */
  static boolean mapPalindromePermutation(String s) {
    Map<Character, Long> charactersCount = countCharacters(s);
    boolean firstOddFound = false;
    for (Long count : charactersCount.values()) {
      if (count % 2 != 0) {
        if (firstOddFound) return false;
        else firstOddFound = true;
      }
    }
    return true;
  }

  /**
   * Sort based solution.
   */
  static boolean sortPalindromePermutation(String s) {
    if (s.length() < 2) return true;

    char[] sorted = s.toLowerCase().replaceAll(" ", "").toCharArray();
    Arrays.sort(sorted);
    int count = 1;
    char current = sorted[0];
    boolean firstOddFound = false;
    for (int i = 1; i < sorted.length; i++) {
      if (sorted[i] == current) {
        count++;
      } else {
        if (count % 2 != 0) {
          if (firstOddFound) return false;
          else firstOddFound = true;
        }
        count = 1;
        current = sorted[i];
      }
    }
    return count % 2 == 0 || !firstOddFound;
  }

  private static Map<Character, Long> countCharacters(String s) {
    return s.toLowerCase()
            .chars()
            .filter(c -> c != ' ')
            .mapToObj(c -> (char)c)
            .collect(groupingBy(Function.identity(), counting()));
  }

  public static void main(String[] args) {
    String tactcoa = "Tact Coa";
    String aaaaa = "aaA aa";
    String aaxaa = "Xaa aa";
    String aaxxaa = "aaa xaX";
    String aacxaa = "aca xaa";
    String abcxaa = "acB xaa";

    System.out.printf("Map: %s = %b\n", tactcoa, mapPalindromePermutation(tactcoa));
    System.out.printf("Map: %s = %b\n", aaaaa, mapPalindromePermutation(aaaaa));
    System.out.printf("Map: %s = %b\n", aaxaa, mapPalindromePermutation(aaxaa));
    System.out.printf("Map: %s = %b\n", aaxxaa, mapPalindromePermutation(aaxxaa));
    System.out.printf("Map: %s = %b\n", aacxaa, mapPalindromePermutation(aacxaa));
    System.out.printf("Map: %s = %b\n", abcxaa, mapPalindromePermutation(abcxaa));

    System.out.println();
    System.out.printf("Sort: %s = %b\n", tactcoa, sortPalindromePermutation(tactcoa));
    System.out.printf("Sort: %s = %b\n", aaaaa, sortPalindromePermutation(aaaaa));
    System.out.printf("Sort: %s = %b\n", aaxaa, sortPalindromePermutation(aaxaa));
    System.out.printf("Sort: %s = %b\n", aaxxaa, sortPalindromePermutation(aaxxaa));
    System.out.printf("Sort: %s = %b\n", aacxaa, sortPalindromePermutation(aacxaa));
    System.out.printf("Sort: %s = %b\n", abcxaa, sortPalindromePermutation(abcxaa));
  }
}

