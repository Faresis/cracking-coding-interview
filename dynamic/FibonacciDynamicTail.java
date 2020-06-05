public class FibonacciDynamicTail {
  public static long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0) return 0;
    if (n == 1) return 1;

    long a = 0;
    long b = 1;
    for (int i = 2; i < n; i++) {
      long c = a + b;
      a = b;
      b = c;
    }
    return a + b;
  }

  public static void main(String[] args) {
    int size = 100_000;
    for (int i = 0; i < size; i++)
      System.out.println(i + ": " + fib(i));
  }
}
