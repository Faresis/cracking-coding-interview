class OneEditAwayString {
  private static final int REMOVED = 1;
  private static final int REPLACED = 0;
  private static final int INSERTED = -1;

  static boolean isOneEditAway(String s1, String s2) {
    int diff = s1.length() - s2.length();
    if (diff < -1 || diff > 1) return false;
    switch (diff) {
      case REMOVED:
        return verifyRemoved(s1, s2, 1);
      case INSERTED:
        return verifyInserted(s1, s2, 1);
      case REPLACED:
        return verifyReplaced(s1, s2, 1);
      default:
        throw new IllegalArgumentException("Unsupported operation.");
    }
  }

  private static boolean verifyRemoved(String from, String to, int numRemoved) {
    int fromIdx = 0;
    int toIdx = 0;
    int numSkipped = 0;
    while (fromIdx < from.length() && toIdx < to.length()) {
      if (from.charAt(fromIdx) == to.charAt(toIdx)) {
        toIdx++;
      } else {
        numSkipped++;
      }
      fromIdx++;
    }
    return numSkipped <= numRemoved;
  }

  private static boolean verifyInserted(String from, String to, int numInserted) {
    return verifyRemoved(to, from, numInserted);
  }

  private static boolean verifyReplaced(String from, String to, int numReplaced) {
    int idx = 0;
    int numSkipped = 0;
    while (idx < from.length()) {
      if (from.charAt(idx) != to.charAt(idx))
        numSkipped++;
      idx++;
    }
    return numSkipped <= numReplaced;
  }

  public static void main(String[] args) {
    System.out.println("(pale, pale): " + isOneEditAway("pale", "pale"));
    System.out.println("(pale, ple): " + isOneEditAway("pale", "ple"));
    System.out.println("(pale, pl): " + isOneEditAway("pale", "pl"));
    System.out.println("(pale, pales): " + isOneEditAway("pale", "pales"));
    System.out.println("(pale, paless): " + isOneEditAway("pale", "paless"));
    System.out.println("(pale, bale): " + isOneEditAway("pale", "bale"));
    System.out.println("(pale, bake): " + isOneEditAway("pale", "bake"));
  }
}

