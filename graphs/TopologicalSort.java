import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TopologicalSort {
  private static class Graph {
    List<Node> nodes = new LinkedList<>();

    int size() {
      return this.nodes.size();
    }

    List<Node> findAll(Predicate<Node> p) {
      return this.nodes.stream().filter(p).collect(Collectors.toList());
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

    g.print();

    System.out.println(buildOrder(g));
  }
}

