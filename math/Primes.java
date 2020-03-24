public class Primes {
  public static void main(String[] args) {
    System.out.println("Is Prime naive.");
    for (int i = 2; i < 20; i++)
      System.out.println(i + ": " + isPrimeNaive(i));

    System.out.println("Is Prime improved.");
    for (int i = 2; i < 20; i++)
      System.out.println(i + ": " + isPrimeImproved(i));

    System.out.println("Sieve of Eratosthenes.");
    boolean[] primes = primes(20);
    for (int i = 2; i < primes.length; i++)
      System.out.println(i + ": " + primes[i]);
  }

  private static boolean isPrimeNaive(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) return false;
    }
    return true;
  }

  private static boolean isPrimeImproved(int n) {
    for (int i = 2; i*i <= n; i++)
      if (n % i == 0) return false;
    return true;
  }

  private static boolean[] primes(int max) {
    boolean[] primes = new boolean[max + 1];
    for (int i = 2; i < primes.length; i++)
      primes[i] = true;

    int prime = 2;
    while (prime*prime <= max) {
      crossOff(primes, prime);
      prime = nextPrime(primes, prime);
    }
    return primes;
  }

  private static void crossOff(boolean[] primes, int prime) {
    for (int i = prime*prime; i < primes.length; i += prime)
      primes[i] = false;
  }

  private static int nextPrime(boolean[] primes, int prime) {
    int next = prime + 1;
    while (next < primes.length && !primes[next])
      ++next;
    return next;
  }
}

