public class FibonacciMemo {
  public static long fib(int n) {
    return fib(n, new long[n+1]);
  }

  public static long fib(int n, long[] memo) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0) return 0;
    if (n == 1) return 1;
    if (memo[n] == 0) memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    return memo[n];
  }

  public static void main(String[] args) {
    int size = 100;
    for (int i = 0; i < size; i++)
      System.out.println(i + ": " + fib(i));
  }
}
