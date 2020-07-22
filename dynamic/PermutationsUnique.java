import java.util.List;
import java.util.LinkedList;

public class PermutationsUnique {
  public static void main(String[] args) {
    System.out.println("PermutationsRec ab: " + permutationsRec("ab"));
    System.out.println("PermutationsRec abc: " + permutationsRec("abc"));
    System.out.println("PermutationsRec abcd: " + permutationsRec("abcd"));
  }

  static List<String> permutationsRec(String s) {
    if (s.length() == 1) return List.of(s);

    List<String> result = new LinkedList<>();
    for (char c : s.toCharArray()) {
      for (String perm : permutationsRec(new StringBuilder(s).deleteCharAt(s.indexOf(c)).toString())) {
        result.add(String.valueOf(c) + perm);
      }
    }
    return result;
  }
}

