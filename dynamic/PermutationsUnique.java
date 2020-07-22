import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;
import java.util.List;
import java.util.LinkedList;

public class PermutationsUnique {
  public static void main(String[] args) {
    System.out.println("PermutationsRec ab: " + permutationsRec("ab"));
    System.out.println("PermutationsRec abc: " + permutationsRec("abc"));
    System.out.println("PermutationsRec abcd: " + permutationsRec("abcd"));

    System.out.println("PermutationsIterative ab: " + permutationsIterative("ab"));
    System.out.println("PermutationsIterative abc: " + permutationsIterative("abc"));
    System.out.println("PermutationsIterative abcd: " + permutationsIterative("abcd"));
  }

  static List<String> permutationsRec(String s) {
    if (s.length() < 2) return List.of(s);

    List<String> result = new LinkedList<>();
    for (char c : s.toCharArray()) {
      for (String perm : permutationsRec(new StringBuilder(s).deleteCharAt(s.indexOf(c)).toString())) {
        result.add(String.valueOf(c) + perm);
      }
    }
    return result;
  }

  static List<String> permutationsIterative(String s) {
    if (s.length() < 2) return List.of(s);

    List<List<Character>> mutations = List.of(List.of(s.charAt(0)));
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      mutations = mutations.stream()
                           .flatMap(m -> insertInAllPositions(m, c).stream())
                           .collect(toList());
    }
    return mutations.stream()
                    .map(m -> m.stream()
                               .map(String::valueOf)
                               .collect(joining()))
                    .collect(toList());
                   
  }

  static List<List<Character>> insertInAllPositions(List<Character> list, Character c) {
    List<List<Character>> result = new LinkedList<>();
    for (int i = 0; i <= list.size(); i++) {
      List<Character> mutation = new LinkedList<>(list);
      mutation.add(i, c);
      result.add(mutation);
    }
    return result;
  }
}

