import java.util.Arrays;

public class MinHeightTree {
  private static class Tree {
    Node root;

    Tree(Node root) { this.root = root; }

    @Override public String toString() { return this.root.toString(); }
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

  public static void main(String[] args) {
    int[] arr = {10, 24, 31, 42, 57, 63, 79, 82, 98, 105};

    System.out.println("Array: " + Arrays.toString(arr));
    System.out.println("Tree: " + toTree(arr));
  }
}
