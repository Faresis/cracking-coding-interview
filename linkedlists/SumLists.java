/**
 *  Sum Lists: you have two numbers represented by a linked list,
 *  where each node contains a single digit. The digits are stored
 *  in reverse order such that the first digit is at the head of
 *  the list. Write a function that adds the two numbers and returns
 *  the sum as a linked list.
 */
public class SumLists {
  private static class Pair<K, V> {
    K first;
    V second;

    Pair(K first, V second) {
      this.first = first;
      this.second = second;
    }
  }

  private static class Node {
    Node next;
    int data;

    Node() {}
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
    System.out.println("Reverse: ");
    Node first = Node.toList(7,1,6);
    Node second = Node.toList(5,9,2);

    Node.print(first);
    Node.print(second);
    Node.print(sum(first, second)); // 617 + 295 = 912 -> 219

    first = Node.toList(9,7,8); // 978
    second = Node.toList(6,8,5); // 685

    Node.print(first);
    Node.print(second);
    Node.print(sum(first, second)); // 879 + 586 = 1465 -> 5641 

    first = Node.toList(9,7,8,1,3);
    second = Node.toList(6,8,5);

    Node.print(first);
    Node.print(second);
    Node.print(sum(first, second)); // 31879 + 586 = 32465 -> 56423

    System.out.println("Forward: ");
    first = Node.toList(6,1,7);
    second = Node.toList(2,9,5);

    Node.print(first);
    Node.print(second);
    Node.print(sumForward(first, second)); // 617 + 295 = 912 -> 912

    first = Node.toList(8,7,9);
    second = Node.toList(5,8,6);

    Node.print(first);
    Node.print(second);
    Node.print(sumForward(first, second)); // 879 + 586 = 1465 -> 1465

    first = Node.toList(3,1,8,7,9);
    second = Node.toList(5,8,6);

    Node.print(first);
    Node.print(second);
    Node.print(sumForward(first, second)); // 31879 + 586 = 32465 -> 32465
  }

  static Node sum(Node a, Node b) {
    Node resultHead = null;
    Node resultTail = null;
    int next = 0;
    while(a != null || b != null) {
      int sum = next+sumData(a,b);
      next = sum/10;
      int val = sum%10;
      resultTail = add(resultTail, val);
      if (resultHead == null) resultHead = resultTail;
      if (a != null) a = a.next;
      if (b != null) b = b.next;
    }
    if (next > 0) resultTail = add(resultTail, next);
    return resultHead;
  }

  static Node sumForward(Node a, Node b) {
    int diff = size(a) - size(b);
    while (diff > 0) {
      b = addZero(b);
      diff--;
    }
    while (diff < 0) {
      a = addZero(a);
      diff++;
    }
    Pair<Integer, Node> child = sumForwardRec(a,b);
    if (child == null)
      return new Node();
    Node result = null;
    if (child.first > 0) {
      result = new Node(child.first);
      result.next = child.second;
    } else {
      result = child.second;
    }
    return result;
  }

  static int sumData(Node a, Node b) {
    int sum = 0;
    if (a != null) sum += a.data;
    if (b != null) sum += b.data;
    return sum;
  }

  static Node add(Node node, int value) {
    if (node == null) {
      return new Node(value);
    } else {
      node.next = new Node(value);
      return node.next;
    }
  }

  static Pair<Integer, Node> sumForwardRec(Node a, Node b) {
    if (a == null && b == null)
      return null;

    Pair<Integer, Node> child = sumForwardRec(a == null ? null : a.next, b == null ? null : b.next);
    Node result = new Node();
    result.next = child == null ? null : child.second;
    int next = child == null ? 0 : child.first;
    int sum = next+sumData(a,b);
    next = sum/10;
    int val = sum%10;
    result.data = val;
    
    return new Pair<Integer, Node>(next, result);
  }

  static int size(Node node) {
    int count = 0;
    while (node != null) {
      node = node.next;
      count++;
    }
    return count;
  }

  static Node addZero(Node node) {
    return new Node(0, node);
  }
}

