import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

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
  }
}

