public class NextNumber {
  public static void main(String[] args) {
    System.out.println("Next number 13");
    next(13);
    System.out.println("Next number 476");
    next(476);
    System.out.println("Next number 3");
    next(3);
    System.out.println("Next number 1");
    next(1);
    System.out.println("Next number 7");
    next(7);
    System.out.println("Next number 0");
    next(0);
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

