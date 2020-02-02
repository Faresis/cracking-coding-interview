import java.util.Arrays;

public class Successor {
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
      if (curr.left != null) curr.left.parent = curr;
      if (curr.right != null) curr.right.parent = curr;
      return curr;
    }
  }

  private static class Node {
    int data;
    int lvl; // for toString only
    Node left;
    Node right;
    Node parent;

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

  public static Node next(Node node) {
    if (node == null) return null;
    if (node.right != null) return mostLeftChild(node.right);
    else return leftParent(node);
  }

  private static Node mostLeftChild(Node node) {
    while (node.left != null) node = node.left;
    return node;
  }

  private static Node leftParent(Node node) {
    while (node.parent != null) {
      if (node.parent.left == node) return node.parent;
      else node = node.parent;
    }
    return null;
  }

  public static void main(String[] args) {
    int[] arr = {10, 24, 31, 42, 57, 63, 79, 82, 98, 105};

    Tree tree = Tree.toTree(arr);
    System.out.println("Array: " + Arrays.toString(arr));
    System.out.println("Tree: " + tree);

    System.out.printf("Successor of %d is %d \n", tree.root.data, next(tree.root).data);
    System.out.printf("Successor of %d is %d \n", tree.root.right.left.left.data, next(tree.root.right.left.left).data);
    System.out.printf("Successor of %d is %d \n", tree.root.right.data, next(tree.root.right).data);
    System.out.printf("Successor of %d is %d \n", tree.root.left.left.left.data, next(tree.root.left.left.left).data);
    System.out.printf("Successor of %d is %d \n", tree.root.left.right.data, next(tree.root.left.right).data);

    Node node = tree.root.left.left.left;
    while (node != null) {
      System.out.println(node.data);
      node = next(node);
    }
  }
}

