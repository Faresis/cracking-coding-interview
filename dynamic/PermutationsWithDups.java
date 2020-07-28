import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Set;
import java.util.LinkedList;

public class PermutationsWithDups {
  public static void main(String[] args) {
    System.out.println("Permutaions with Set - abb: " + permutationsWithSet("abb"));
    System.out.println("Permutaions with Set - abbb: " + permutationsWithSet("abbb"));
    System.out.println("Permutaions with Set - adbb: " + permutationsWithSet("adbb"));
    System.out.println("Permutaions with Set - ab: " + permutationsWithSet("ab"));
    System.out.println("Permutaions with Set - abc: " + permutationsWithSet("abc"));
    System.out.println("Permutaions with Set - abcc: " + permutationsWithSet("abcc"));
    System.out.println("Permutaions with Set - aba: " + permutationsWithSet("aba"));
    System.out.println("Permutaions with Set - abaa: " + permutationsWithSet("abaa"));
  }

  static List<String> permutationsWithSet(String s) {
    if (s.length() < 2) return List.of(s);
    Set<List<Character>> mutations = Set.of(List.of(s.charAt(0)));
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      mutations = mutations.stream()
                           .flatMap(m -> insertInAllPositions(m, c).stream())
                           .collect(toSet());
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

