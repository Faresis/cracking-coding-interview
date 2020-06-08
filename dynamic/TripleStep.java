public class TripleStep {
  public static void main(String[] args) {
    System.out.println("Recursive solution: ");
    for (int i = 0; i < 37; i++)
      System.out.println(i + ": " + countWays(i));

    System.out.println("Dynamic solution: ");
    for (int i = 0; i < 200; i++)
      System.out.println(i + ": " + countWaysDynamic(i));
  }

  public static long countWays(int steps) {
    if (steps < 1) return 0;
    return countWaysRec(steps);
  }
  
  private static long countWaysRec(int steps) {
    if (steps == 0) return 1;
    if (steps < 0) return 0;
    return countWaysRec(steps - 1) + countWaysRec(steps - 2) + countWaysRec(steps - 3);
  }

  public static long countWaysDynamic(int steps) {
    if (steps <= 0) return 0;
    if (steps == 1) return 1;
    if (steps == 2) return 2;
    if (steps == 3) return 4;
    long a = 1;
    long b = 2;
    long c = 4;
    for (int i = 4; i <= steps; i++) {
      long d = a+b+c;
      a = b;
      b = c;
      c = d;
    }
    return c;
  }
}
