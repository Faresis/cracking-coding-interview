/**
 *  Intersection: Given two(singly) linked lists, determine if the two lists intersect.
 *  Return the intersecting node.
 */
public class Intersection {
  private static class Node {
    char data;
    Node next;

    Node() {}
    Node(char data) { this.data = data; }

    static Node toSinglyLinkedList(String word) {
      Node first = null;
      Node current = null;
      for (char c : word.toCharArray()) {
        Node node = new Node(c);
        if (current == null) first = node;
        if (current != null) current.next = node;
        current = node;
      }
      return first;
    }

    static void join(Node src, Node dst, int point) {
      while (src.next != null) src = src.next;
      while (point-- > 0) dst = dst.next;
      src.next = dst;
    }

    static int length(Node node) {
      int len = 0;
      while (node != null) {
        len++;
        node = node.next;
      }
      return len;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      Node node = this;
      while (node != null) {
        sb.append(node.data);
        node = node.next;
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    Node first = Node.toSinglyLinkedList("ABCDEF");
    Node second = Node.toSinglyLinkedList("GHIJ");
    Node.join(second, first, 3);

    System.out.println(first);  // abcdef
    System.out.println(second); // ghijdef

    System.out.println("Brute: " + intersectBrute(first, second).data);
  }

  /**
   *  Returns intersection of two singly lists.
   *  R:O(n^2), S:O(1).
   */
  static Node intersectBrute(Node first, Node second) {
    while (first != null) {
      Node inner = second;
      while (inner != null) {
        if (first == inner) return first;
        inner = inner.next;
      }
      first = first.next;
    }
    return null;
  }
}
