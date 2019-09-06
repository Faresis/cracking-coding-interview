import java.util.*;

class CompressionString {
  static String compress(String orig) {
    if (orig.isEmpty()) return orig;

    int count = 1;
    StringBuilder sb = new StringBuilder();
    char current = orig.charAt(0);
    for (int i = 1; i < orig.length(); i++) {
      if (orig.charAt(i) == current) {
        count++;
      } else {
        sb.append(current + String.valueOf(count));
        current = orig.charAt(i);
        count = 1;
      }
    }
    sb.append(current + String.valueOf(count));
    String compressed = sb.toString();
    return compressed.length() < orig.length() ? compressed : orig;
  }

  public static void main(String[] args) {
    System.out.println("aabcccccaaa: " + compress("aabcccccaaa"));
    System.out.println("abc: " + compress("abc"));
  }
}

