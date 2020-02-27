public class LongestSequence {
  public static void main(String[] args) {
    System.out.println("For 1775: " + longest(1775)); 
    System.out.println("For 0: " + longest(0)); 
    System.out.println("For 1: " + longest(1)); 
    System.out.println("For 01s: " + longest((~0 >>> 1))); 
    System.out.println("For 0s110s: " + longest((6 << 5))); 
    System.out.println("For 1011111011110111: " + longest(0b1011111011110111)); 


    System.out.println("For 1775 book: " + longestBook(1775)); 
    System.out.println("For 0 book: " + longestBook(0)); 
    System.out.println("For 1 book: " + longestBook(1)); 
    System.out.println("For 01s book: " + longestBook((~0 >>> 1))); 
    System.out.println("For 0s110s book: " + longestBook((6 << 5))); 
    System.out.println("For 1011111011110111 book: " + longestBook(0b1011111011110111)); 
  }

  public static int longestBook(int num) {
    int max = 1;
    int previous = 0;
    int current = 0;
    while (num > 0) {
      if ((num & 1) == 1) current++;
      if ((num & 1) == 0) {
         previous = (num & 2) == 0 ? 0 : current;
         current = 0;
      }
      max = Math.max(max, current + previous + 1);
      num /= 2;
    }
    return max;
  }

  public static int longest(int num) {
    int max = 0;
    boolean isFirstSequence = true;
    int firstSequence = 0;
    int secondSequence = 0;
    while (num > 0) {
      int digit = num & 1;
      if (digit == 1) {
        if (isFirstSequence) firstSequence++;
        else secondSequence++;
      } else { // digit == 0
        if (isFirstSequence && firstSequence > 0) {
          isFirstSequence = false;
        } else if (!isFirstSequence && secondSequence == 0) {
          isFirstSequence = true;
          if (max < ++firstSequence) max = firstSequence;
          firstSequence = 0;
        } else if (!isFirstSequence && secondSequence > 0) {
          firstSequence = firstSequence + secondSequence + 1;
          if (max < firstSequence) max = firstSequence;
          firstSequence = secondSequence;
          secondSequence = 0;
        } // handles 1011111011110111
      }
      num /= 2;
    }
    firstSequence = firstSequence + secondSequence + 1;
    if (max < firstSequence) max = firstSequence;
    return max;
  }
}

