public class Insertion {
  public static void main(String[] args) {
    System.out.println(insert(1024, 19, 2, 6));
  }

  public static int insert(int n, int m, int i, int j) {
    int mask = (-1 << j) | ((1 << i) - 1);
    return (n & mask) | (m << i);
  }
}

