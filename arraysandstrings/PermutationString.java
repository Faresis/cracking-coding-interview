import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

class PermutationString {
  /**
   * Brute solution.
   */
  static boolean brutePermutation(String s1, String s2) {
    if (s1.length() != s2.length())
      return false;
    
    for (char c : s1.toCharArray())
      if (count(c, s1) != count(c, s2))
        return false;
    return true;
  }

  /**
   * Map solution.
   */
  static boolean mapPermutation(String s1, String s2) {
    Map<Character, Long> s1Map = s1.chars().mapToObj(c -> (char)c).collect(groupingBy(Function.identity(), counting()));
    Map<Character, Long> s2Map = s2.chars().mapToObj(c -> (char)c).collect(groupingBy(Function.identity(), counting()));
    return s1Map.equals(s2Map);
  }

  /**
   * Sort solution.
   */
  static boolean sortPermutation(String s1, String s2) {
    if (s1.length() != s2.length())
      return false;

    char[] sorted1 = s1.toCharArray();
    Arrays.sort(sorted1);
    char[] sorted2 = s2.toCharArray();
    Arrays.sort(sorted2);

    for (int i = 0; i < sorted1.length; i++)
      if (sorted1[i] != sorted2[i])
        return false;
    return true;
  }

  public static void main(String[] args) {
    String abc = "abc";
    String aaa = "aaa";
    String aab = "aab";
    String bba = "bba";
    String cba = "cba";
    String cbaa = "cbaa";
    
    System.out.println("Brute abc aaa: " + brutePermutation(abc, aaa));
    System.out.println("Brute aaa abc: " + brutePermutation(aaa, abc));
    System.out.println("Brute abc cba: " + brutePermutation(abc, cba));
    System.out.println("Brute cba abc: " + brutePermutation(cba, abc));
    System.out.println("Brute abc cbaa: " + brutePermutation(abc, cbaa));
    System.out.println("Brute cbaa abc: " + brutePermutation(cbaa, abc));
    System.out.println("Brute aab bba: " + brutePermutation(aab, bba));
    System.out.println("Brute bba aab: " + brutePermutation(bba, aab));

    System.out.println();
    System.out.println("Map abc aaa: " + mapPermutation(abc, aaa));
    System.out.println("Map aaa abc: " + mapPermutation(aaa, abc));
    System.out.println("Map abc cba: " + mapPermutation(abc, cba));
    System.out.println("Map cba abc: " + mapPermutation(cba, abc));
    System.out.println("Map abc cbaa: " + mapPermutation(abc, cbaa));
    System.out.println("Map cbaa abc: " + mapPermutation(cbaa, abc));
    System.out.println("Map aab bba: " + mapPermutation(aab, bba));
    System.out.println("Map bba aab: " + mapPermutation(bba, aab));

    System.out.println();
    System.out.println("Sort abc aaa: " + sortPermutation(abc, aaa));
    System.out.println("Sort aaa abc: " + sortPermutation(aaa, abc));
    System.out.println("Sort abc cba: " + sortPermutation(abc, cba));
    System.out.println("Sort cba abc: " + sortPermutation(cba, abc));
    System.out.println("Sort abc cbaa: " + sortPermutation(abc, cbaa));
    System.out.println("Sort cbaa abc: " + sortPermutation(cbaa, abc));
    System.out.println("Sort aab bba: " + sortPermutation(aab, bba));
    System.out.println("Sort bba aab: " + sortPermutation(bba, aab));
  }

  private static int count(char c, String s) {
    int count = 0;
    int pos = 0;
    while ((pos = s.indexOf(c, pos)) >= 0) {
      count++; pos++;
    }
    return count;
  }
}

