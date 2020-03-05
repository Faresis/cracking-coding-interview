public class Conversion {
  public static void main(String[] args) {
    System.out.println("29 to 15: " + conversion(29, 15));
  }

  public static int conversion(int a, int b) {
    int diff = a ^ b;
    int count = 0;
    while (diff > 0) {
      if ((diff & 1) == 1) count++;
      diff >>= 1;
    }
    return count;
  }
}

