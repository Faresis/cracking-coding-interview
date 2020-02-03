import java.util.Arrays;

public class IsBinarySearchTree {
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

  public static boolean isBst(Tree tree) {
    return isBst(tree.root);
  }

  private static boolean isBst(Node node) {
    if (node == null) return true;
    boolean left = isBst(node.left);
    if (!left) return left;
    boolean right = isBst(node.right);
    if (!right) return right;
    boolean result = true;
    if (node.left != null) result &= node.left.data < node.data;
    if (node.right != null) result &= node.right.data > node.data;
    return result;
  }

  public static boolean isBstRev(Tree tree) {
    return isBstRev(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBstRev(Node node, int min, int max) {
    if (node == null) return true;
    boolean result = true;
    result &= node.data > min;
    result &= node.data < max;
    result &= isBstRev(node.left, min, node.data);
    result &= isBstRev(node.right, node.data, max);
    return result;
  }

  public static void main(String[] args) {
    int[] arr = {10, 24, 31, 42, 57, 63, 79, 82, 98, 105};

    Tree tree = Tree.toTree(arr);
    System.out.println("Array: " + Arrays.toString(arr));
    System.out.println("Tree: " + tree);
    System.out.println("Is bst: " + isBst(tree));
    System.out.println("Is bst revised: " + isBstRev(tree));

    tree.root.left.right.data = 67;

    System.out.println("Tree: " + tree);
    System.out.println("Is bst: " + isBst(tree));
    System.out.println("Is bst revised: " + isBstRev(tree));

    tree.root.left.right.data = 57;
    tree.root.left.left.data = 70;

    System.out.println("Tree: " + tree);
    System.out.println("Is bst: " + isBst(tree));
    System.out.println("Is bst revised: " + isBstRev(tree));

    tree.root.left.left.data = 24;
    tree.root.right.data = 62;

    System.out.println("Tree: " + tree);
    System.out.println("Is bst: " + isBst(tree));
    System.out.println("Is bst revised: " + isBstRev(tree));

    tree.root.right.data = 83;

    System.out.println("Tree: " + tree);
    System.out.println("Is bst: " + isBst(tree));
    System.out.println("Is bst revised: " + isBstRev(tree));
  }
}

