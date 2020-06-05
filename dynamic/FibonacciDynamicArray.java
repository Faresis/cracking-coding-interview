public class FibonacciDynamicArray {
  public static long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0) return 0;
    if (n == 1) return 1;

    long[] memo = new long[n];
    memo[0] = 0;
    memo[1] = 1;
    for (int i = 2; i < n; i++)
      memo[i] = memo[i-1] + memo[i-2];
    return memo[n-1] + memo[n-2];
  }

  public static void main(String[] args) {
    int size = 100_000;
    for (int i = 0; i < size; i++)
      System.out.println(i + ": " + fib(i));
  }
}
