import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Stack;
import java.util.Iterator;

public class TopologicalSort {
  private static class Graph {
    List<Node> nodes = new LinkedList<>();

    int size() {
      return this.nodes.size();
    }

    List<Node> findAll(Predicate<Node> p) {
      return this.nodes.stream().filter(p).collect(Collectors.toList());
    }

    Node findAny(Predicate<Node> p) {
      return this.nodes.stream().filter(p).findAny().orElse(null);
    }

    boolean isEmpty() {
      return this.nodes.isEmpty();
    }

    Node get(String key) {
      for (Node n : nodes)
        if (n.data.equals(key))
           return n;
      return null;
    }

    Node add(String key) {
      Node n = new Node(key);
      this.nodes.add(n);
      return n;
    }

    Node getOrAdd(String key) {
      Node result = this.get(key);
      if (result == null) result = this.add(key);
      return result;
    }

    void print() {
      for (Node n : this.nodes)
        System.out.println(n.print());
    }

    static Graph toGraph(Map<String, String> deps) {
      Graph g = new Graph();
      for (Map.Entry<String, String> pair : deps.entrySet()) {
        Node src = g.getOrAdd(pair.getKey());
        Node dst = g.getOrAdd(pair.getValue());
        src.to(dst);
      }
      return g;
    }
  }

  private static class Node {
    enum State {
      NOT_VISITED,
      VISITED,
      IN_PROGRESS
    }

    State state = State.NOT_VISITED;
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

  public static Set<Node> buildOrder(Graph graph) {
    List<Node> free = graph.findAll(n -> n.sources.isEmpty());
    Set<Node> result = new LinkedHashSet<>();
    while (!free.isEmpty()) {
      result.addAll(free);
      free = free.stream()
                 .flatMap(n -> n.destinations.stream())
                 .filter(d -> !result.contains(d))
                 .filter(d -> d.sources.stream().noneMatch(s -> !result.contains(s)))
                 .collect(Collectors.toList());
    }
    if (graph.size() != result.size()) throw new IllegalArgumentException("Can't be built in order");
    return result;
  }

  public static Stack<Node> buildOrderDfs(Graph graph) {
    Node free = graph.findAny(n -> n.state == Node.State.NOT_VISITED);
    Stack<Node> result = new Stack<>();
    while (free != null) {
      buildOrderDfs(free, result);
      free = graph.findAny(n -> n.state == Node.State.NOT_VISITED);
    }
    return result;
  }

  private static void buildOrderDfs(Node node, Stack<Node> result) {
    if (node.state == Node.State.VISITED) return;
    if (node.state == Node.State.IN_PROGRESS) throw new IllegalArgumentException("Can't be built in order");

    node.state = Node.State.IN_PROGRESS;
    for (Node d : node.destinations) {
      buildOrderDfs(d, result);
    }
    result.push(node);
    node.state = Node.State.VISITED;
  }

  public static void main(String[] args) {
    Map<String, String> deps = new HashMap<>();
    deps.put("a", "d");
    deps.put("f", "b");
    deps.put("b", "d");
    deps.put("f", "a");
    deps.put("d", "c");

    Graph g = Graph.toGraph(deps);
    g.getOrAdd("a");
    g.getOrAdd("b");
    g.getOrAdd("c");
    g.getOrAdd("d");
    g.getOrAdd("e");
    g.getOrAdd("f");

    System.out.println("Graph");
    g.print();

    System.out.println("Topological.");
    Iterator<Node> it = buildOrder(g).iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
      it.remove();
    }

    System.out.println("Topological dfs.");
    Stack<Node> s = buildOrderDfs(g);
    while (!s.isEmpty()) {
      System.out.println(s.pop());
    }
  }
}

