public class PairwiseSwap {
  public static void main(String[] args) {
    System.out.println("Swap 1010101010: " + swap(0b1010101010));
    System.out.println("Swap 1010111110: " + swap(0b1010111110));
    System.out.println("Swap 1010000000: " + swap(0b1010000000));
    System.out.println("Swap 0: " + swap(0));
    System.out.println("Swap 1: " + swap(1));
    System.out.println("Swap ~0: " + swap(~0));
  }

  public static int swap(int num) {
    int mask = 3;
    for (int i = 0; i < 16; i++) {
      if ((num | mask) != num && (num & (~mask)) != num) {
        num ^= mask;
      }
      mask <<= 2;
    }
    return num;
  }
}

