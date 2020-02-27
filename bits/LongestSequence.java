public class LongestSequence {
  public static void main(String[] args) {
    System.out.println("For 1775: " + longest(1775)); 
    System.out.println("For 0: " + longest(0)); 
    System.out.println("For 1: " + longest(1)); 
    System.out.println("For 01s: " + longest((~0 >>> 1))); 
    System.out.println("For 0s110s: " + longest((6 << 5))); 
    System.out.println("For 1011111011110111: " + longest(0b1011111011110111)); 
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

