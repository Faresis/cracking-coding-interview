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

