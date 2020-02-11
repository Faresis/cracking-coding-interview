import java.util.Set;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.List;
import java.util.LinkedList;

public class BstSequence {
  private static class Tree {
    Node root;

    Tree(Node root) { this.root = root; }

    @Override public String toString() { return this.root.toString(); }

    public boolean hasDuplicates() {
      return hasDuplicates(this.root, new HashSet<Integer>());
    }

    public void inOrder(Consumer<Node> c) {
      inOrder(this.root, c);
    }

    private static void inOrder(Node node, Consumer<Node> c) {
      if (node == null) return;
      inOrder(node.left, c);
      inOrder(node.right, c);
    }

    public static boolean hasDuplicates(Node node, Set<Integer> set) {
      if (node == null) return false;
      if (!set.add(node.data)) return true;

      return hasDuplicates(node.left, set) || hasDuplicates(node.right, set);
    }

    public static Tree create(int lvl) {
      return new Tree(create(1, lvl));
    }

    private static Node create(int id, int lvl) {
      if (--lvl < 0) return null;

      Node node = new Node(id);
      node.left = create(id*2, lvl);
      node.right = create(id*2 + 1, lvl); 
      return node;
    }
  }

  private static class Node {
    int data;
    int lvl; // for toString only
    Node left;
    Node right;

    Node(int data) { this.data = data; }

    @Override public String toString() {
      String result = "";
      if (this.left != null) {
        this.left.lvl = this.lvl + 1;
        result += this.left.toString();
      }
      result += " Level: " + this.lvl + " Data: "  + this.data;
      if (this.right != null) {
        this.right.lvl = this.lvl + 1;
        result += this.right.toString();
      }
      return result;
    }
  }

  private static int count = 0;
  public static void bstSequence(Tree tree) {
    List<Node> sequence = new LinkedList<>();
    List<Node> routes = new LinkedList<>();
    bstSequence(tree.root, routes, sequence);
    System.out.println("Count: " + count);
  }

  private static void bstSequence(Node node, List<Node> routes, List<Node> sequence) {
    if (node == null) return;
    if (node.left != null) routes.add(node.left);
    if (node.right != null) routes.add(node.right);

    sequence.add(node);

    if (routes.isEmpty()) {
      System.out.println("Possible array: ");
      sequence.stream().forEach(n -> System.out.print(n.data + " "));
      System.out.println();
      count++;
    } else {
      for (Node route : routes) {
        List<Node> newRoutes = new LinkedList<>(routes);
        newRoutes.remove(route);
        bstSequence(route, newRoutes, new LinkedList<>(sequence));
      }
    }
  }

  public static void main(String[] args) {
    Tree tree = Tree.create(4);
    System.out.println("Tree: " + tree);
    System.out.println("Has duplicates: " + tree.hasDuplicates());
    tree.inOrder(n -> System.out.println(n.data));

    bstSequence(tree);
  }
}

