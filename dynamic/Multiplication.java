public class Multiplication {
  public static void main(String[] args) {
    System.out.println("V1:");
    System.out.println("6*0:" + multiply_v1(6, 0));
    System.out.println("0*5:" + multiply_v1(0, 5));
    System.out.println("1*5:" + multiply_v1(1, 5));
    System.out.println("6*1:" + multiply_v1(6, 1));
    System.out.println("6*5:" + multiply_v1(6, 5));
    System.out.println("6*6:" + multiply_v1(6, 6));
    System.out.println("7*8:" + multiply_v1(7, 8));
    System.out.println("1024*1024:" + multiply_v1(1024, 1024));
    System.out.println("Calls v1: " + calls_v1);

    System.out.println("V2:");
    System.out.println("6*0:" + multiply_v2(6, 0));
    System.out.println("0*5:" + multiply_v2(0, 5));
    System.out.println("1*5:" + multiply_v2(1, 5));
    System.out.println("6*1:" + multiply_v2(6, 1));
    System.out.println("6*5:" + multiply_v2(6, 5));
    System.out.println("6*6:" + multiply_v2(6, 6));
    System.out.println("7*8:" + multiply_v2(7, 8));
    System.out.println("1024*1024:" + multiply_v2(1024, 1024));
    System.out.println("Calls v2: " + calls_v2);
  }

  static long calls_v1 = 0;
  static long multiply_v1(int a, int b) {
    calls_v1 = 0;
    if (a == 0 || b == 0) return 0;
    if (a == 1) return b;
    if (b == 1) return a;
    return multiply_v1_rec(a, b, 0);
  }

  private static long multiply_v1_rec(int a, int b, long result) {
    calls_v1++;
    if (b == 0) return result;
    return multiply_v1_rec(a, --b, result+a);
  }

  static long calls_v2 = 0;
  static long multiply_v2(int a, int b) {
    calls_v2 = 0;
    return multiply_v2_rec(a, b, 0);
  }

  private static long multiply_v2_rec(long a, int b, long diff) {
    calls_v2++;
    if (b == 0) return 0;
    if (b == 1) return a - diff;
    if (b % 2 == 0) return multiply_v2_rec(a << 1, b >> 1, diff);
    else return multiply_v2_rec(a + a, (b+1) >> 1, diff + a);
  }
}

