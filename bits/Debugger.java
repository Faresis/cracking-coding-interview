public class Debugger {
  public static void main(String[] args) {
    for (int i = 0; i <= 256; i++)
      System.out.println(i + ": " + check(i));
  }

  public static boolean check(int n) {
    return (n & (n - 1)) == 0;
  }
}

