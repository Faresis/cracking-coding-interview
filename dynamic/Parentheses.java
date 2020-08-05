import static java.util.stream.Collectors.toSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Parentheses {
  public static void main(String[] args) {
    System.out.println("Parentheses 1: " + parentheses(1));
    System.out.println("Parentheses 2: " + parentheses(2));
    System.out.println("Parentheses 3: " + parentheses(3));
    System.out.println("Parentheses 4: " + parentheses(4));

    System.out.println("Parentheses rec 1: " + parenthesesRec(1));
    System.out.println("Parentheses rec 2: " + parenthesesRec(2));
    System.out.println("Parentheses rec 3: " + parenthesesRec(3));
    System.out.println("Parentheses rec 4: " + parenthesesRec(4));
  }

  static List<String> parenthesesRec(int num) {
    return parenthesesRec("", num, num);
  }

  static List<String> parenthesesRec(String prefix, int left, int right) {
    if (left < 0 || right < 0) throw new IllegalArgumentException();
    if (left == 0 && right == 0) return List.of(prefix);

    List<String> result = new LinkedList<>();
    if (left > 0) {
      result.addAll(parenthesesRec(prefix + "(", left - 1, right));
    }
    if (right > left) {
      result.addAll(parenthesesRec(prefix + ")", left, right - 1));
    }
    return result;
  }

  static Set<List<Parens>> parentheses(int n) {
    if (n < 1) return Set.of();
    Set<List<Parens>> combinations = Set.of(List.of(new Parens()));
    while (--n > 0) {
      combinations = combinations.stream()
                                 .flatMap(c ->
                                          Stream.concat(
                                            wrap(c).stream(),
                                            Stream.of(
                                              insertFirst(c),
                                              insertLast(c)
                                            )
                                          )
                                 )
                                 .collect(toSet());
    }
    return combinations;
  }

  static List<List<Parens>> wrap(List<Parens> list) {
    List<List<Parens>> result = new LinkedList<>();
    for (int i = 0; i < list.size(); i++) {
      List<Parens> clone = new LinkedList<>(list);
      clone.set(i, new Parens(List.of(clone.get(i))));
      result.add(clone);
    }
    result.add(List.of(new Parens(list)));
    return result;
  }

  static List<Parens> insertFirst(List<Parens> list) {
    LinkedList<Parens> amended = new LinkedList<>(list);
    amended.addFirst(new Parens());
    return amended;
  }

  static List<Parens> insertLast(List<Parens> list) {
    LinkedList<Parens> amended = new LinkedList<>(list);
    amended.addLast(new Parens());
    return amended;
  }

  static class Parens {
    final List<Parens> children;

    Parens() { this(List.of()); }
    Parens(List<Parens> children) {
      this.children = new LinkedList<>(children);
    }

    @Override
    public int hashCode() {
      return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      Parens other = null;
      if (obj instanceof Parens) {
        other = (Parens) obj;
      }
      if (other == null) return false;

      return this.toString().equals(other.toString());
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder("(");
      for (Parens child : children) {
        builder.append(child.toString());
      }
      builder.append(")");
      return builder.toString();
    }
  }
}

