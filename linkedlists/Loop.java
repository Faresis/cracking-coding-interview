/**
 *  Loop Detection: Given a linked list which might contain a loop, implement an algorithm that returns
 *  the code at the beginning of the loop (if one exists).
 */
public class Loop {
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

    static int length(Node node) {
      int len = 0;
      while (node != null) {
        len++;
        node = node.next;
      }
      return len;
    }

    static Node get(Node node, int idx) {
      while (idx-- > 0) node = node.next;
      return node;
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
    Node cycle = Node.toSinglyLinkedList("abcdefghijklmnopq");
    Node nocycle = Node.toSinglyLinkedList("abcdefghijklmnopq");

    System.out.println(cycle);

    Node k = Node.get(cycle, 10);
    Node q = Node.get(cycle, 16);
    // cycle
    q.next = k;

    System.out.println("Brute: " + findCycleBrute(cycle).data);
    System.out.println("Brute: " + findCycleBrute(nocycle));
    System.out.println("Pattern: " + findCyclePattern(cycle).data);
    System.out.println("Pattern: " + findCyclePattern(nocycle));
  }

  /**
   *  Finds a cycle in a singly linked list if one exists.
   *  R:O(n), S:O(1).
   */
  static Node findCyclePattern(Node list) {
    Node fast = list.next;
    Node slow = list;
    Node flashpoint = null;
    int tick = 1;
    while (fast != null && fast != slow) {
      fast = fast.next;
      slow = ++tick % 2 == 0 ? slow.next : slow;
    }

    if (fast == null) return null; // no cycle

    flashpoint = fast.next;
    slow = list;
    while (flashpoint != slow) {
      flashpoint = flashpoint.next;
      slow = slow.next;
    }
    return flashpoint;
  }

  /**
   *  Finds a cycle in a singly linked list if one exists.
   *  R:O(n^2), S:O(1).
   */
  static Node findCycleBrute(Node list) {
    Node fast = list.next;
    Node slow = list;
    int len = 1;
    while (fast != slow && fast != null) {
      fast = fast.next;
      len++;
      if (len % 3 == 0) slow = slow.next;
    }

    if (fast == null) return null; // no cycle;

    int tick = 1;
    fast = list.next;
    slow = list;
    while (fast != slow) {
      fast = fast.next;
      tick++;
      if (tick % len == 0) slow = slow.next;
    }
    return slow;
  }
}

