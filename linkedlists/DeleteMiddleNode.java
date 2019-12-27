/**
 *  Delete middle node: implement an algorithm to delete a node in the middle of a singly linked list,
 *  given only access to that node.
 */
public class DeleteMiddleNode {
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

    static void print(Node node) {
      while(node != null) {
        System.out.println(node);
        node = node.next;
      }
    }
  }

  public static void main(String[] args) {
    Node list = new Node("first", new Node("second", new Node("third", new Node("fourth", new Node("fifth")))));

    Node.print(list);
    deleteMiddleNode(list.next.next);
    Node.print(list); // 1,2,4,5
  }

  /**
   *  Deletes provided node from the middle of a singly linked list.
   *  R:O(n), S:O(1).
   */
  static void deleteMiddleNode(Node node) {
    Node prev = node;
    Node curr = node;
    while (curr.next != null) {
      prev = curr;
      curr = curr.next;
      prev.data = curr.data;
    }
    prev.next = null;
  } 
}

