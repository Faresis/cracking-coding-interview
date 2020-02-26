public class Insertion {
  public static void main(String[] args) {
    System.out.println(insert(
                              1024, // 10000000000
                              19,   // 10011
                              2,
                              6));
  }

  public static int insert(int n, int m, int i, int j) {
    // 1s0000000 OR 0s11 = 1s0000011
    int mask = (-1 << ++j) | ((1 << i) - 1);

    // clear bits i to j in n
    // then shift m to i bits left
    // set m bits in n from i to j with OR
    return (n & mask) | (m << i);
  }
}

