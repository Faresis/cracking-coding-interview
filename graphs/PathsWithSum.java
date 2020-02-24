import java.util.LinkedList;
import java.util.HashMap;

public class PathsWithSum {
  private static class Tree {
    Node root;

    Tree(Node root) { this.root = root; }

    @Override public String toString() { return this.root.toString(); }

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

  public static int countSumBook(Tree tree, int targetSum) {
    return countSumBook(tree.root, new HashMap<Integer, Integer>(), 0, targetSum);
  }

  private static int countSumBook(Node node, HashMap<Integer, Integer> paths, int runningSum, int targetSum) {
    if (node == null) return 0;

    runningSum += node.data;
    int rootSum = runningSum - targetSum;
    int pathsCount = paths.getOrDefault(rootSum, 0);

    if (runningSum == targetSum) pathsCount++;

    addPath(paths, runningSum);
    pathsCount += countSumBook(node.left, paths, runningSum, targetSum);
    pathsCount += countSumBook(node.right, paths, runningSum, targetSum);
    removePath(paths, runningSum);
    return pathsCount;
  }

  private static void addPath(HashMap<Integer, Integer> paths, int path) {
    int val = paths.getOrDefault(path, 0);
    paths.put(path, ++val);
  }

  private static void removePath(HashMap<Integer, Integer> paths, int path) {
    int val = paths.getOrDefault(path, 0);
    if (val == 0)
      paths.remove(path);
    else
      paths.put(path, --val);
  }

  public static int countSum(Tree tree, int sum) {
    return countSum(tree.root, new LinkedList<Node>(), 0, sum);
  }

  public static int countSum(Node node, LinkedList<Node> seq, int seqSum, int searchSum) {
    if (node == null) return 0;
    seq.addLast(node);
    seqSum += node.data;

    LinkedList<Node> currSeq = new LinkedList<>(seq);
    int currSeqSum = seqSum;
    int result = 0;
    while (!currSeq.isEmpty()) {
      if (currSeqSum == searchSum) result++;
      currSeqSum -= currSeq.removeFirst().data;
    }
    return result
           + countSum(node.left, new LinkedList<Node>(seq), seqSum, searchSum)
           + countSum(node.right, new LinkedList<Node>(seq), seqSum, searchSum);
  }

  //                    1
  //        2                      3
  //   4         5            6         7
  //  8 9      10 11        12 13     14 15
  public static void main(String[] args) {
    Tree tree = Tree.create(4);
    System.out.println("Tree: " + tree);

    System.out.println("Sum 7 count: " + countSum(tree, 7));
    System.out.println("Sum 7 count book: " + countSumBook(tree, 7));

    tree.root.left.right.left.data = -1;
    System.out.println("Sum 7 count: " + countSum(tree, 7));
    System.out.println("Sum 7 count book: " + countSumBook(tree, 7));

    tree.root.right.left.right.data = -3;
    System.out.println("Sum 7 count: " + countSum(tree, 7));
    System.out.println("Sum 7 count book: " + countSumBook(tree, 7));

    System.out.println("Sum 0 count: " + countSum(tree, 0));
    System.out.println("Sum 0 count book: " + countSumBook(tree, 0));

    tree.root.left.left.left.data = -15;
    System.out.println("Sum -15 count: " + countSum(tree, -15));
    System.out.println("Sum -15 count book: " + countSumBook(tree, -15));

    tree.root.data = -18;
    System.out.println("Sum 25 count: " + countSum(tree, 25));
    System.out.println("Sum 25 count book: " + countSumBook(tree, 25));

    tree.root.right.data = 18;
    tree.root.right.right.right.data = 77;
    System.out.println("Sum 84 count: " + countSum(tree, 84));
    System.out.println("Sum 84 count book: " + countSumBook(tree, 84));
  }
}

