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
  }

  static Set<List<Parens>> parentheses(int n) {
    if (n < 1) return Set.of();
    Set<List<Parens>> combinations = Set.of(List.of(new Parens()));
    while (--n > 0) {
      combinations = combinations.stream()
                                 .flatMap(c ->
                                          Stream.of(
                                            wrap(c),
                                            insertFirst(c),
                                            insertLast(c)
                                          )
                                 )
                                 .collect(toSet());
    }
    return combinations;
  }

  static List<Parens> wrap(List<Parens> list) {
    return List.of(new Parens(list));
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

