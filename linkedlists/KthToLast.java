/**
 * Return Kth to last: Implement an algorithm to find Kth to last element of a singly linked list.
 */
public class KthToLast {
  private static class Node {
    Node next;
    String data;

    Node(String data) {
      this.data = data;
    }
    Node(String data, Node next) {
      this.data = data;
      this.next = next;
    }

    @Override
    public String toString() {
      return this.data;
    }
  }

  public static void main(String[] args) {
    Node list = new Node("first", new Node("second", new Node("third", new Node("fourth", new Node("fifth")))));

    System.out.println(getKthToLast(list, 1)); // fourth
    System.out.println(getKthToLast(list, 3)); // second
  }

  /**
   * Gets Kth node to the last. R:O(n), S:O(1).
   */
  static Node getKthToLast(Node node, int k) {
    Node shifted = node;
    Node head = node;

    while(head.next != null) {
      head = head.next;
      if (k > 0) k--;
      else shifted = shifted.next;
    }
    return shifted;
  }
}

