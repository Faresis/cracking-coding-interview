public class Multiplication {
  public static void main(String[] args) {
    System.out.println("V1:");
    System.out.println("6*5:" + multiply_v1(6, 5));
    System.out.println("7*8:" + multiply_v1(7, 8));
  }

  static long multiply_v1(int a, int b) {
    if (a == 0 || b == 0) return 0;
    if (a == 1) return b;
    if (b == 1) return a;
    return multiply_v1_rec(a, b, 0);
  }

  private static long multiply_v1_rec(int a, int b, long result) {
    if (b == 0) return result;
    return multiply_v1_rec(a, --b, result+a);
  }
}

