import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class RandomNode {
  private static class Tree {
    Node root;

    Tree(Node root) { this.root = root; }

    @Override public String toString() { return this.root.toString(); }

    public Node getRandom() {
      int idx = new Random().nextInt(this.root.count) + 1;
      return getNode(idx, this.root);
    }

    private static Node getNode(int idx, Node node) {
      if (idx < 1 || node == null) throw new IllegalArgumentException();
      if (idx == 1) return node;
      if (node.left == null) return getNode(idx - 1, node.right);
      if (node.right == null) return getNode(idx - 1, node.left);
      int diff = idx - 1 - node.left.count;
      return diff > 0 ? getNode(diff, node.right) : getNode(idx - 1, node.left);
    }

    public static Tree create(int lvl) {
      return new Tree(create(1, lvl));
    }

    private static Node create(int id, int lvl) {
      if (--lvl < 0) return null;

      Node node = new Node(id);
      node.left = create(id*2, lvl);
      if (node.left != null) node.count += node.left.count;
      node.right = create(id*2 + 1, lvl); 
      if (node.right != null) node.count += node.right.count;
      return node;
    }
  }

  private static class Node {
    int data;
    int count; // count of nodes in the subtree
    int lvl; // for toString only
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
      this.count = 1;
    }

    @Override public String toString() {
      String result = "";
      if (this.left != null) {
        this.left.lvl = this.lvl + 1;
        result += this.left.toString();
      }
      result += "{ Level: " + this.lvl + " Data: "  + this.data + " Count: " + this.count + " }\n";
      if (this.right != null) {
        this.right.lvl = this.lvl + 1;
        result += this.right.toString();
      }
      return result;
    }
  }

  private static void fill(Map<Node, Integer> counts, Tree tree) {
    for (int i = 0; i < 1_000_000; i++) {
      Node node = tree.getRandom();
      if (counts.containsKey(node))
        counts.put(node, counts.get(node) + 1);
      else
        counts.put(node, 1);
    }
  }

  private static void print(Map<Node, Integer> map) {
    for (Map.Entry<Node, Integer> entry : map.entrySet()) {
      System.out.println("Node: " + entry.getKey().data + " Count: " + entry.getValue());
    }
  }

  public static void main(String[] args) {
    Tree balanced = Tree.create(4);
    System.out.println("Balanced: ");
    Map<Node, Integer> balancedCounts = new HashMap<>();
    fill(balancedCounts, balanced);
    print(balancedCounts);

    Tree unbalanced = Tree.create(7);
    unbalanced.root.left.left.count -= unbalanced.root.left.left.left.count;
    unbalanced.root.left.count -= unbalanced.root.left.left.left.count;
    unbalanced.root.count -= unbalanced.root.left.left.left.count;
    unbalanced.root.left.left.left = null;
    System.out.println("Unbalanced: ");
    Map<Node, Integer> unbalancedCounts = new HashMap<>();
    fill(unbalancedCounts, unbalanced);
    print(unbalancedCounts);
  }
}

