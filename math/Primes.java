public class Primes {
  public static void main(String[] args) {
    System.out.println("Is Prime naive.");
    for (int i = 1; i < 20; i++)
      System.out.println(i + ": " + isPrimeNaive(i));
  }

  private static boolean isPrimeNaive(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) return false;
    }
    return true;
  }
}

