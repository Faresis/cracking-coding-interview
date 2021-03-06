public class Fibonacci {
  public static int fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    int size = 100;
    for (int i = 0; i < size; i++)
      System.out.println(i + ": " + fib(i));
  }
}
