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

  /**
   * Sorting solution.
   */
  static boolean sortingUnique(String s) {
    char[] sorted = s.toCharArray();
    Arrays.sort(sorted);
    for (int i = 1; i < sorted.length; i++) 
      if (sorted[i-1] == sorted[i])
        return false;
    return true;
  }

  /**
   * BitSet solution.
   */
  static boolean bitsetUnique(String s) {
    BitSet bitSet = new BitSet();
    for (char c : s.toCharArray()) {
      if(bitSet.get(c)) return false;
      bitSet.set(c);
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

    System.out.println("Sorting abcde: " + sortingUnique(abcde));
    System.out.println("Sorting withDuplicates: " + sortingUnique(withDuplicates));

    System.out.println("BitSet abcde: " + bitsetUnique(abcde));
    System.out.println("BitSet withDuplicates: " + bitsetUnique(withDuplicates));
  }
}

