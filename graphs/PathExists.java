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

  private static boolean bfs(Node start, Node end) {
    Queue<Node> bfs = new LinkedList<>();
    Set<Node> visited = new HashSet<>();
    bfs.add(start);
    while (!bfs.isEmpty()) {
      Node current = bfs.remove();
      if (current == end) return true;
      if (visited.add(current)) bfs.addAll(current.destinations);
    }
    return false;
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

    System.out.println("Path exists (two way) 'a -> i': " + twoWaySearch(g.get("a"), g.get("i")));
    System.out.println("Path exists (two way) 'a -> e': " + twoWaySearch(g.get("a"), g.get("e")));
    System.out.println("Path exists (two way) 'e -> a': " + twoWaySearch(g.get("e"), g.get("a")));
    System.out.println("Path exists (two way) 'b -> a': " + twoWaySearch(g.get("b"), g.get("a")));
    System.out.println("Path exists (two way) 'c -> a': " + twoWaySearch(g.get("c"), g.get("a")));
    System.out.println("Path exists (two way) 'd -> a': " + twoWaySearch(g.get("d"), g.get("a")));
    System.out.println("Path exists (two way) 'd -> g': " + twoWaySearch(g.get("d"), g.get("g")));
    System.out.println("Path exists (two way) 'a -> x': " + twoWaySearch(g.get("a"), g.get("x")));
    System.out.println("Path exists (two way) 'b -> x': " + twoWaySearch(g.get("b"), g.get("x")));
    System.out.println("Path exists (two way) 'c -> x': " + twoWaySearch(g.get("c"), g.get("x")));
    System.out.println("Path exists (two way) 'd -> x': " + twoWaySearch(g.get("d"), g.get("x")));
    System.out.println("Path exists (two way) 'e -> x': " + twoWaySearch(g.get("e"), g.get("x")));
    System.out.println("Path exists (two way) 'f -> x': " + twoWaySearch(g.get("f"), g.get("x")));
    System.out.println("Path exists (two way) 'g -> x': " + twoWaySearch(g.get("g"), g.get("x")));
    System.out.println("Path exists (two way) 'h -> x': " + twoWaySearch(g.get("h"), g.get("x")));
    System.out.println("Path exists (two way) 'i -> x': " + twoWaySearch(g.get("i"), g.get("x")));

    System.out.println("Path exists (bfs) 'a -> i': " + bfs(g.get("a"), g.get("i")));
    System.out.println("Path exists (bfs) 'a -> e': " + bfs(g.get("a"), g.get("e")));
    System.out.println("Path exists (bfs) 'e -> a': " + bfs(g.get("e"), g.get("a")));
    System.out.println("Path exists (bfs) 'b -> a': " + bfs(g.get("b"), g.get("a")));
    System.out.println("Path exists (bfs) 'c -> a': " + bfs(g.get("c"), g.get("a")));
    System.out.println("Path exists (bfs) 'd -> a': " + bfs(g.get("d"), g.get("a")));
    System.out.println("Path exists (bfs) 'd -> g': " + bfs(g.get("d"), g.get("g")));
    System.out.println("Path exists (bfs) 'a -> x': " + bfs(g.get("a"), g.get("x")));
    System.out.println("Path exists (bfs) 'b -> x': " + bfs(g.get("b"), g.get("x")));
    System.out.println("Path exists (bfs) 'c -> x': " + bfs(g.get("c"), g.get("x")));
    System.out.println("Path exists (bfs) 'd -> x': " + bfs(g.get("d"), g.get("x")));
    System.out.println("Path exists (bfs) 'e -> x': " + bfs(g.get("e"), g.get("x")));
    System.out.println("Path exists (bfs) 'f -> x': " + bfs(g.get("f"), g.get("x")));
    System.out.println("Path exists (bfs) 'g -> x': " + bfs(g.get("g"), g.get("x")));
    System.out.println("Path exists (bfs) 'h -> x': " + bfs(g.get("h"), g.get("x")));
    System.out.println("Path exists (bfs) 'i -> x': " + bfs(g.get("i"), g.get("x")));
  }
}

