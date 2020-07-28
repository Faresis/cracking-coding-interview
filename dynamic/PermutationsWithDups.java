import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.concat;

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

    System.out.println("Permutaions - abb: " + permutations("abb"));
    System.out.println("Permutaions - abbb: " + permutations("abbb"));
    System.out.println("Permutaions - adbb: " + permutations("adbb"));
    System.out.println("Permutaions - ab: " + permutations("ab"));
    System.out.println("Permutaions - abc: " + permutations("abc"));
    System.out.println("Permutaions - abcc: " + permutations("abcc"));
    System.out.println("Permutaions - aba: " + permutations("aba"));
    System.out.println("Permutaions - abaa: " + permutations("abaa"));
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

  static List<String> permutations(String s) {
    if (s.length() < 2) return List.of(s);
    List<List<Character>> mutations = List.of(List.of(s.charAt(0)));
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      if (mutations.get(0).contains(c)) {
        mutations = insertDuplicate(mutations, c);
      } else {
        mutations = mutations.stream()
                             .flatMap(m -> insertInAllPositions(m, c).stream())
                             .collect(toList());
      }
    }
    return mutations.stream()
                    .map(m -> m.stream()
                               .map(String::valueOf)
                               .collect(joining()))
                    .collect(toList());
  }

  static List<List<Character>> insertDuplicate(List<List<Character>> mutations, Character c) {
    return concat(
             addToTail(mutations, c).stream(),
             concat(
               doubleChar(mutations.stream()
                                   .filter(m -> !m.get(m.size() - 1).equals(c))
                                   .collect(toList()), c).stream(),
               addToHead(mutations.stream()
                                  .filter(m -> !m.get(0).equals(c) && !m.get(m.size() - 1).equals(c))
                                  .collect(toList()), c).stream()))
           .collect(toList());
  }

  static List<List<Character>> addToTail(List<List<Character>> mutations, Character c) {
    return mutations.stream()
                    .map(LinkedList::new)
                    .map(m -> { m.add(c); return m; })
                    .collect(toList());
  }

  static List<List<Character>> doubleChar(List<List<Character>> mutations, Character c) {
    return mutations.stream()
                    .map(LinkedList::new)
                    .map(m -> {
                      int i = m.indexOf(c);
                      m.add(i, c);
                      return m;
                    })
                    .collect(toList());
              
  }

  static List<List<Character>> addToHead(List<List<Character>> mutations, Character c) {
    return mutations.stream()
                    .map(LinkedList::new)
                    .map(m -> { m.add(0, c); return m; })
                    .collect(toList());
  }
}

