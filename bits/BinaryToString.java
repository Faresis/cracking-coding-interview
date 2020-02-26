public class BinaryToString {
  public static void main(String[] args) {
    System.out.println("0.72 in binary: " + toString(0.72d));
    System.out.println("0.5 in binary: " + toString(0.5d));
    System.out.println("0.25 in binary: " + toString(0.25d));
    System.out.println("0.125 in binary: " + toString(0.125d));
  }

  public static String toString(double num) {
    if (num < 0 || num >= 1) return "Error";

    StringBuilder sb = new StringBuilder("0.");
    do {
      if (sb.length() == 32) return sb.toString();

      num *= 2;
      int val = (int) num;
      sb.append(val);
      num -= val;
    } while (num > 0);
    return sb.toString();
  }
}

