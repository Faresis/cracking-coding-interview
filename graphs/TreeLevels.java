import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TreeLevels {
  private static class Tree {
    Node root;

    Tree(Node root) { this.root = root; }

    @Override public String toString() { return this.root.toString(); }

    public static Tree toTree(int[] arr) {
      return new Tree(toTree(arr, 0, arr.length));
    }

    private static Node toTree(int[] arr, int start, int end) {
      if (start == end) return null;

      int mid = (end - start)/2 + start;
      Node curr = new Node(arr[mid]);
      curr.left = toTree(arr, start, mid);
      curr.right = toTree(arr, mid+1, end);
      return curr;
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

  public static List<List<Integer>> toLevels(Tree tree) {
    List<List<Integer>> levels = new ArrayList<>();
    toLevels(levels, tree.root, 1);
    return levels;
  }

  private static void toLevels(List<List<Integer>> levels, Node node, int idx) {
    if (node == null) return;

    int lvl = toLevel(idx);
    if (lvl >= levels.size()) levels.add(lvl, new LinkedList<>());
    levels.get(lvl).add(node.data);
    toLevels(levels, node.left, idx * 2);
    toLevels(levels, node.right, idx * 2 + 1);
  }

  public static List<List<Integer>> toLevelsBook(Tree tree) {
    List<List<Integer>> result = new LinkedList<>();
    List<Node> current = new LinkedList<>();
    if (tree.root != null) current.add(tree.root);
    while (!current.isEmpty()) {
      result.add(toInts(current));
      List<Node> parents = current;
      current = new LinkedList<>();
      for (Node parent : parents) {
        if (parent.left != null) current.add(parent.left);
        if (parent.right != null) current.add(parent.right);
      }
    }
    return result;
  }

  private static List<Integer> toInts(List<Node> nodes) {
    return nodes.stream().map(n -> n.data).collect(Collectors.toList());
  }

  private static int toLevel(int idx) {
    return (int) (Math.log10(idx) / Math.log10(2));
  }

  public static void main(String[] args) {
    int[] arr = {10, 24, 31, 42, 57, 63, 79, 82, 98, 105};

    Tree tree = Tree.toTree(arr);
    System.out.println("Array: " + Arrays.toString(arr));
    System.out.println("Tree: " + tree);
    System.out.println("Levels: " + toLevels(tree));
    System.out.println("Levels book: " + toLevelsBook(tree));
  }
}

