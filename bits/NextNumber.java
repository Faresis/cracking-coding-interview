public class NextNumber {
  public static void main(String[] args) {
    System.out.println("Next number 13");
    next(13);
    System.out.println("Next number revised 13");
    nextRevised(13);

    System.out.println("Next number 3");
    next(3);
    System.out.println("Next number revised 3");
    nextRevised(3);

    System.out.println("Next number 1");
    next(1);
    System.out.println("Next number revised 1");
    nextRevised(1);

    System.out.println("Next number 7");
    next(7);
    System.out.println("Next number revised 7");
    nextRevised(7);

    System.out.println("Next number 0");
    next(0);
    System.out.println("Next number revised 0");
    nextRevised(0);

    System.out.println("Next number 111011100");
    next(0b111011100);
    System.out.println("Next number revised 111011100");
    nextRevised(0b111011100);

    System.out.println("Next number 111000111");
    next(0b111000111);
    System.out.println("Next number revised 111000111");
    nextRevised(0b111000111);
  }

  public static void nextRevised(int num) {
    nextLargestRevised(num);
    nextSmallestRevised(num);
  }

  private static void nextSmallestRevised(int num) {
    int curr = 0;
    int ones = 0;
    while (curr < 31) {
      if ((num & (3 << curr)) == (2 << curr)) {
        System.out.println(toSmallest(num, curr, ones));
        return;
      }
      if ((num & (1 << curr)) == (1 << curr)) ones++;
      curr++;
    }
    System.out.println(num);
  }

  private static void nextLargestRevised(int num) {
    int curr = 0;
    int ones = 0;
    while (curr < 31) {
      if ((num & (3 << curr)) == (1 << curr)) {
        System.out.println(toLargest(num, curr, ones));
        return;
      }
      if ((num & (1 << curr)) == (1 << curr)) ones++;
      curr++;
    }
    System.out.println(num);
  }

  private static int toSmallest(int num, int cutoff, int ones) {
    // flip two cutoff bits
    num = num ^ (3 << cutoff);
    if (ones > 0) {
      // clear pre-cutoff bits
      num = num & ((~0) << cutoff);
      // set rightmost bits to specified number of 1s
      num = num | ~(((~0) << cutoff) | ((1 << (cutoff - ones)) - 1));
    }
    return num;
  }

  private static int toLargest(int num, int cutoff, int ones) {
    // flip two cutoff bits
    num = num ^ (3 << cutoff);
    if (ones > 0) {
      // clear pre-cutoff bits
      num = num & ((~0) << cutoff);
      // set rightmost bits to specified number of 1s
      num = num | ((1 << ones) - 1);
    }
    return num;
  }

  public static void next(int num) {
    nextLargest(num);
    nextSmallest(num);
  }

  private static void nextSmallest(int num) {
    int idx = -1;
    int curr = 0;
    while (curr < 32) {
      if ((num & (1 << curr)) == 0 && idx < 0) idx = curr;
      if ((num & (1 << curr)) > 0 && idx >= 0) {
        num = num ^ (1 << curr);
        num = num ^ (1 << (curr - 1));
        System.out.println(num);
        return;
      }
      curr++;
    }
    System.out.println(num);
  }

  private static void nextLargest(int num) {
    int idx = -1;
    int curr = 0;
    while (curr < 32) {
      if ((num & (1 << curr)) > 0 && idx < 0) idx = curr;
      if ((num & (1 << curr)) == 0 && idx >= 0) {
        num = num ^ (1 << idx);
        num = num ^ (1 << curr);
        System.out.println(num);
        return;
      }
      curr++;
    }
    System.out.println(num);
  }
}

