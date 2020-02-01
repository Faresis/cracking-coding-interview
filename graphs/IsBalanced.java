import java.util.Arrays;

public class IsBalanced {
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

  public static boolean isBalanced(Tree tree) {
    int count = getCount(tree.root);
    return count >= 0;
  }

  private static int getCount(Node node) {
    if (node == null) return 0;
    int left = getCount(node.left);
    if (left < 0) return left;
    int right = getCount(node.right);
    if (right < 0) return right;
    int diff = left - right;
    if (diff > 1 || diff < -1) return -1;
    return left + right + 1;
  }

  public static boolean isBalancedBook(Tree tree) {
    int height = getHeight(tree.root);
    return height >= 0;
  }

  private static int getHeight(Node node) {
    if (node == null) return 0;
    int left = getHeight(node.left);
    if (left < 0) return left;
    int right = getHeight(node.right);
    if (right < 0) return right;
    if (Math.abs(left - right) > 1) return -1;
    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {
    int[] arr = {10, 24, 31, 42, 57, 63, 79, 82, 98, 105};

    Tree tree = Tree.toTree(arr);
    System.out.println("Array: " + Arrays.toString(arr));
    System.out.println("Tree: " + tree);
    System.out.println("Is balanced: " + isBalanced(tree));
    System.out.println("Is balanced book: " + isBalancedBook(tree));

    tree.root.left.left = null;

    System.out.println("Tree: " + tree);
    System.out.println("Is balanced : " + isBalanced(tree));
    System.out.println("Is balanced book: " + isBalancedBook(tree));

    tree.root.left.left = Tree.toTree(new int[]{ 10, 11, 12, 13, 14, 15, 16}).root;

    System.out.println("Tree: " + tree);
    System.out.println("Is balanced : " + isBalanced(tree));
    System.out.println("Is balanced book: " + isBalancedBook(tree));
  }
}

