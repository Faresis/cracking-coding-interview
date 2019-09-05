import java.util.*;

class UniqueString {
  /**
   * Brute solution.
   */
  static boolean bruteUnique(String s) {
    for (int i = 0; i < s.length(); i++) {
      for (int j = i+1; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j))
          return false;
      }
    }
    return true;
  }

  /**
   * HashSet solution.
   */
  static boolean setUnique(String s) {
    Set<Character> set = new HashSet<>();
    for(char c : s.toCharArray()) {
      if (!set.add(c))
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    String abcde = "abcde";
    String withDuplicates = "withDuplicates";

    System.out.println("Brute abcde: " + bruteUnique(abcde));
    System.out.println("Brute withDuplicates: " + bruteUnique(withDuplicates));

    System.out.println("Set abcde: " + setUnique(abcde));
    System.out.println("Set withDuplicates: " + setUnique(withDuplicates));
  }
}

