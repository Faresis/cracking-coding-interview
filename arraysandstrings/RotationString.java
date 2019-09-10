class RotationString {
  static boolean isRotation(String s1, String s2) {
    if (s1.length() != s2.length()) return false;
    int s1Idx = s1.length() - 1;
    int s2Idx = s2.length() - 1;
    while (s1Idx > 0) {
      if (s1.charAt(s1Idx) == s2.charAt(s2Idx)) {
        --s2Idx; --s1Idx;
      } else {
        if (s2Idx == s2.length() - 1) {
          --s1Idx;
        } else {
          ++s2Idx;
        }
      }
    }
    if (s2Idx == 1) return true;
    return s2.substring(0, s2Idx).equals(s1.substring(s1.length() - s2Idx));
  }

  static boolean isRotationSubstr(String s1, String s2) {
    int len = s1.length();
    if (len > 0 && len == s2.length()) {
      String concat = s1 + s1;
      return concat.indexOf(s2) >= 0;
    }
    return false;
  }

  public static void main(String[] args) {
    String s1 = "waterbottle";
    String s2 = "erbottlewat";
    System.out.printf("isRotation %s : %s -> %s\n", s1, s2, isRotation(s1, s2));
    System.out.printf("isRotationSubstr %s : %s -> %s\n", s1, s2, isRotationSubstr(s1, s2));

    s1 = "taxat";
    s2 = "xatta";
    System.out.printf("isRotation %s : %s -> %s\n", s1, s2, isRotation(s1, s2));
    System.out.printf("isRotationSubstr %s : %s -> %s\n", s1, s2, isRotationSubstr(s1, s2));

    s1 = "taxar";
    s2 = "xatra";
    System.out.printf("isRotation %s : %s -> %s\n", s1, s2, isRotation(s1, s2));
    System.out.printf("isRotationSubstr %s : %s -> %s\n", s1, s2, isRotationSubstr(s1, s2));
  }
}
