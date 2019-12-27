/**
 *  Partition: write code to partition a linked list around a value x such that all nodes less than
 *  x come befoe all nodes greater than or equal to x.
 */
public class Partition {
  private static class Node {
    Node next;
    int data;

    Node(int data) {
      this.data = data;
    }
    Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    @Override
    public String toString() {
      return String.valueOf(this.data);
    }

    static Node toList(int... values) {
      Node first = new Node(values[0]);
      Node curr = first;
      for (int i = 1; i < values.length; i++) {
        Node next = new Node(values[i]);
        curr.next = next;
        curr = next;
      }
      return first;
    }

    static void print(Node node) {
      System.out.println();
      while(node != null) {
        System.out.print(node);
        System.out.print('\t');
        node = node.next;
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Node list = Node.toList(3,5,8,5,10,2,1);

    Node.print(list);
    Node.print(partition(list, 5));
  }

  /**
   *  Partitions a singly linked list based on the specified value.
   *  R:O(n), S:O(1).
   */
  static Node partition(Node node, int part) {
    Node leftHead = null;
    Node leftTail = null;
    Node rightHead = null;
    Node rightTail = null;
    while(node != null) {
      Node curr = node;
      node = node.next;
      curr.next = null;
      if (curr.data < part) {
        if (leftTail == null) {
          leftHead = curr;
        } else {
          leftTail.next = curr;
        }
        leftTail = curr;
      } else {
        if (rightTail == null) {
          rightHead = curr;
        } else {
          rightTail.next = curr;
        }
        rightTail = curr;
      }
    }
    Node result = null;
    if (leftHead != null) result = leftHead;
    if (rightHead != null) {
      if (result == null) {
        result = rightHead;
      } else {
        Node space = new Node(0);
        space.next = new Node(0);
        space.next.next = rightHead;
        leftTail.next = space;
      }
    }
    return result;
  }
}

