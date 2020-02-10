import java.util.HashSet;
import java.util.Set;

public class FirstCommonAncestor {
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

    public static Tree toTree(int[] arr) {
      return new Tree(toTree(arr, 0, arr.length));
    }

    public static Tree create(int lvl) {
      return new Tree(create(1, lvl));
    }

    private static Node create(int id, int lvl) {
      if (--lvl < 0) return null;

      Node node = new Node(id);
      node.left = create(id*2, lvl);
      if (node.left != null) node.left.parent = node;
      node.right = create(id*2 + 1, lvl); 
      if (node.right != null) node.right.parent = node;
      return node;
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

  public static Node fcaWithParent(Node p, Node q) {
    int pHeight = getHeight(p);
    int qHeight = getHeight(q);
    int diff = pHeight - qHeight;
    while (diff-- > 0) p = p.parent;
    while (++diff < 0) q = q.parent;
    while (p != null && p != q) {
      p = p.parent;
      q = q.parent;
    }
    return p;
  } 

  private static class FcaResult {
    boolean firstFound;
    boolean secondFound;
    Node fca;
  }

  public static Node fcaWithoutParent(Tree tree, Node p, Node q) {
    FcaResult r = fcaWithoutParent(tree.root, p, q, new FcaResult());
    if (r.firstFound && r.secondFound && r.fca != null)
      return r.fca;
    else
      return null;
  }

  private static FcaResult fcaWithoutParent(Node tree, Node p, Node q, FcaResult r) {
    if (tree == null) return r;

    if (!r.firstFound) {
      r.firstFound = tree == p;
      if (r.firstFound) {
        r = fcaWithoutParent(p, p, q, r);
        if (r.secondFound) r.fca = tree;
        return r;
      }
    }
    if (!r.firstFound) {
      r = fcaWithoutParent(tree.left, p, q, r);
      if (r.firstFound && !r.secondFound) {
        r.secondFound = tree == q;
        if (!r.secondFound)
          r = fcaWithoutParent(tree.right, p, q, r);
        if (r.secondFound) r.fca = tree;
        return r;
      }
    }
    if (!r.firstFound) {
      r = fcaWithoutParent(tree.right, p, q, r);
      if (r.firstFound && !r.secondFound) {
        r.secondFound = tree == q;
        if (!r.secondFound)
          r = fcaWithoutParent(tree.left, p, q, r);
        if (r.secondFound) r.fca = tree;
        return r;
      }
    }
    if (r.firstFound && !r.secondFound) {
      r.secondFound = tree == q;
      if (!r.secondFound) r = fcaWithoutParent(tree.left, p, q, r);
      if (!r.secondFound) r = fcaWithoutParent(tree.right, p, q, r);
    }
    return r;
  }

  private static int getHeight(Node n) {
    int height = 0;
    while (n.parent != null) {
      height++;
      n = n.parent;
    }
    return height;
  }

  public static void main(String[] args) {
    Tree tree = Tree.create(7);
    System.out.println("Tree: " + tree);
    System.out.println("Has duplicates: " + tree.hasDuplicates());

    Node p = tree.root.left.left.right.right;
    Node q = tree.root.left.right.right.right;
    System.out.println("Expected fca: " + tree.root.left.data);
    System.out.println("Actual fca with parent: " + fcaWithParent(p, q).data);
    System.out.println("Actual fca without parent: " + fcaWithoutParent(tree, p, q).data);

    q = q.left.right;
    System.out.println("Expected fca: " + tree.root.left.data);
    System.out.println("Actual fca with parent: " + fcaWithParent(p, q).data);
    System.out.println("Actual fca without parent: " + fcaWithoutParent(tree, p, q).data);

    p = tree.root.right.left.right.left.right;
    q = tree.root.left.left.right.left.right;
    System.out.println("Expected fca: " + tree.root.data);
    System.out.println("Actual fca with parent: " + fcaWithParent(p, q).data);
    System.out.println("Actual fca without parent: " + fcaWithoutParent(tree, p, q).data);

    p = tree.root.right.left.right;
    q = tree.root.right.left.right.left.right;
    System.out.println("Expected fca: " + tree.root.right.left.right.data);
    System.out.println("Actual fca with parent: " + fcaWithParent(p, q).data);
    System.out.println("Actual fca without parent: " + fcaWithoutParent(tree, p, q).data);

    q = Tree.create(2).root.left;
    System.out.println("Expected fca: " + null);
    System.out.println("Actual fca with parent: " + fcaWithParent(p, q));
    System.out.println("Actual fca without parent: " + fcaWithoutParent(tree, p, q));
  }
}

