public class NextNumber {
  public static void main(String[] args) {
    System.out.println("Next number 13");
    next(13);
    System.out.println("Next number revised 13");
    nextRevised(13);
    System.out.println("Next number arithmetic 13");
    nextArithmetic(13);

    System.out.println("Next number 3");
    next(3);
    System.out.println("Next number revised 3");
    nextRevised(3);
    System.out.println("Next number arithmetic 3");
    nextArithmetic(3);

    System.out.println("Next number 1");
    next(1);
    System.out.println("Next number revised 1");
    nextRevised(1);
    System.out.println("Next number arithmetic 1");
    nextArithmetic(1);

    System.out.println("Next number 7");
    next(7);
    System.out.println("Next number revised 7");
    nextRevised(7);
    System.out.println("Next number arithmetic 7");
    nextArithmetic(7);

    System.out.println("Next number 0");
    next(0);
    System.out.println("Next number revised 0");
    nextRevised(0);
    System.out.println("Next number arithmetic 0");
    nextArithmetic(0);

    System.out.println("Next number 111011100");
    next(0b111011100);
    System.out.println("Next number revised 111011100");
    nextRevised(0b111011100);
    System.out.println("Next number arithmetic 111011100");
    nextArithmetic(0b111011100);

    System.out.println("Next number 111000111");
    next(0b111000111);
    System.out.println("Next number revised 111000111");
    nextRevised(0b111000111);
    System.out.println("Next number arithmetic 111000111");
    nextArithmetic(0b111000111);
  }

  public static void nextArithmetic(int num) {
    nextLargestArithmetic(num);
    nextSmallestArithmetic(num);
  }

  private static void nextLargestArithmetic(int num) {
    int tmp = num;
    int c0 = 0;
    int c1 = 0;
    while ((tmp & 1) == 0 && tmp != 0) {
      c0++;
      tmp >>= 1;
    }
    while ((tmp & 1) == 1) {
      c1++;
      tmp >>= 1;
    }
    if (c1+c0 == 31 || c1 + c0 == 0) {
      System.out.println(num);
      return;
    } else {
      System.out.println(getLargestArithmetic(num, c0, c1));
    }
  }

  private static int getLargestArithmetic(int n, int c0, int c1) {
    return n + (1 << c0) + (1 << (c1 - 1)) - 1;
  }

  private static void nextSmallestArithmetic(int num) {
    int tmp = num;
    int c0 = 0;
    int c1 = 0;
    while ((tmp & 1) == 1) {
      c1++;
      tmp = tmp >> 1;
    }
    if (tmp == 0) {
      System.out.println(num);
      return;
    }
    while ((tmp & 1) == 0 && tmp != 0) {
      c0++;
      tmp = tmp >> 1;
    }
    System.out.println(getSmallestArithmetic(num, c0, c1));
  }

  private static int getSmallestArithmetic(int n, int c0, int c1) {
    return n - (1 << c1) - (1 << (c0 - 1)) + 1;
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

