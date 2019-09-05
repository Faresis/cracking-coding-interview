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

  public static void main(String[] args) {
    String abcde = "abcde";
    String withDuplicates = "withDuplicates";

    System.out.println("Brute abcde: " + bruteUnique(abcde));
    System.out.println("Brute withDuplicates: " + bruteUnique(withDuplicates));
  }
}

