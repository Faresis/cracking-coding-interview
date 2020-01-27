import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.Iterator;
import java.util.Collections;

class PathExists {
  private static class Graph {
    List<Node> nodes = new LinkedList<>();

    Node get(String key) {
      for (Node n : nodes)
        if (n.data.equals(key))
           return n;
      return null;
    }

    void add(char... nodes) {
      Node previous = null;
      for (char c : nodes) {
        Node current = new Node(String.valueOf(c));
        if (previous != null) previous.to(current);
        this.nodes.add(current);
        previous = current;
      }
    }

    void print() {
      for (Node n : this.nodes)
        System.out.println(n.print());
    }
  }

  private static class Node {
    String data;
    List<Node> destinations = new LinkedList<>();
    List<Node> sources = new LinkedList<>();

    Node(String data) { this.data = data; }

    void to(Node to) {
      Node from = this;
      from.destinations.add(to);
      to.sources.add(from);
    }

    String print() {
      return String.format("{ %s, destinations: %s, sources: %s }", this.data, this.destinations, this.sources);
    }

    @Override
    public String toString() {
      return this.data;
    }
  }

  private static boolean twoWaySearch(Node start, Node end) {
    Set<Node> sources = new HashSet<>();
    Set<Node> destinations = new HashSet<>();
    Queue<Node> forward = new LinkedList<>();
    Queue<Node> backward = new LinkedList<>();

    forward.add(start); backward.add(end);
    while (!forward.isEmpty() || !backward.isEmpty()) {
      if (!forward.isEmpty()) {
        Node fwd = forward.remove();
        if (sources.add(fwd)) {
          forward.addAll(fwd.destinations);
        }
      }
      if (!backward.isEmpty()) {
        Node bwd = backward.remove();
        if (destinations.add(bwd)) {
          backward.addAll(bwd.sources);
        }
      }
      if (!Collections.disjoint(sources, destinations)) return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.add('a', 'b', 'c', 'd', 'e');
    g.add('f', 'g', 'h', 'i');
    g.add('x');

    g.get("b").to(g.get("d"));
    g.get("b").to(g.get("e"));
    g.get("a").to(g.get("e"));
    g.get("d").to(g.get("b"));
    g.get("c").to(g.get("a"));

    g.print();

    System.out.println("Path exists 'a -> i': " + twoWaySearch(g.get("a"), g.get("i")));
    System.out.println("Path exists 'a -> e': " + twoWaySearch(g.get("a"), g.get("e")));
    System.out.println("Path exists 'e -> a': " + twoWaySearch(g.get("e"), g.get("a")));
    System.out.println("Path exists 'b -> a': " + twoWaySearch(g.get("b"), g.get("a")));
    System.out.println("Path exists 'c -> a': " + twoWaySearch(g.get("c"), g.get("a")));
    System.out.println("Path exists 'd -> a': " + twoWaySearch(g.get("d"), g.get("a")));
    System.out.println("Path exists 'd -> g': " + twoWaySearch(g.get("d"), g.get("g")));
    System.out.println("Path exists 'a -> x': " + twoWaySearch(g.get("a"), g.get("x")));
    System.out.println("Path exists 'b -> x': " + twoWaySearch(g.get("b"), g.get("x")));
    System.out.println("Path exists 'c -> x': " + twoWaySearch(g.get("c"), g.get("x")));
    System.out.println("Path exists 'd -> x': " + twoWaySearch(g.get("d"), g.get("x")));
    System.out.println("Path exists 'e -> x': " + twoWaySearch(g.get("e"), g.get("x")));
    System.out.println("Path exists 'f -> x': " + twoWaySearch(g.get("f"), g.get("x")));
    System.out.println("Path exists 'g -> x': " + twoWaySearch(g.get("g"), g.get("x")));
    System.out.println("Path exists 'h -> x': " + twoWaySearch(g.get("h"), g.get("x")));
    System.out.println("Path exists 'i -> x': " + twoWaySearch(g.get("i"), g.get("x")));
  }
}

