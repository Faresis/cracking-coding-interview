import java.util.Set;
import java.util.HashSet;

public class IsSubtree {
  private static class Tree {
    Node root;

    Tree(Node root) { this.root = root; }

    @Override public String toString() { return this.root.toString(); }

    public boolean hasDuplicates() {
      return hasDuplicates(this.root, new HashSet<Integer>());
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

  public static boolean isSubtree(Tree t1, Tree t2) {
    return isSubtree(t1.root, t2.root);
  }

  private static boolean isSubtree(Node t1, Node t2) {
    if (t1 == null) return false;
    if (t1.data == t2.data && isEqual(t1, t2)) return true;
    if (isSubtree(t1.left, t2)) return true;
    if (isSubtree(t1.right, t2)) return true;
    return false;
  }

  private static boolean isEqual(Node t1, Node t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;
    return t1.data == t2.data && isEqual(t1.left, t2.left) && isEqual(t1.right, t2.right);
  }

  public static void main(String[] args) {
    Tree tree = Tree.create(4);
    System.out.println("Tree: " + tree);
    System.out.println("Has duplicates: " + tree.hasDuplicates());

    Tree subTree = new Tree(Tree.create(4).root.left.right);
    System.out.println("Subtree: " + subTree);
    System.out.println("Is subtree: " + isSubtree(tree, subTree));

    subTree.root.right.data = 77;
    System.out.println("Subtree: " + subTree);
    System.out.println("Is subtree: " + isSubtree(tree, subTree));

    subTree.root.right = null;
    System.out.println("Subtree: " + subTree);
    System.out.println("Is subtree: " + isSubtree(tree, subTree));
  }
}

