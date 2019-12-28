import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.Stack;

/**
 *  Palindrome: Implement a function to check if a linked list is a palindrome.
 */
public class Palindrome {
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
    System.out.println("Doubly linked: ");
    LinkedList<Character> doublyLinked = toLinkedList("tabat");
    System.out.println(doublyLinked);
    System.out.println("Is palindrome: " + isPalindrome(doublyLinked));

    doublyLinked = toLinkedList("hello");
    System.out.println(doublyLinked);
    System.out.println("Is palindrome: " + isPalindrome(doublyLinked));

    System.out.println();
    System.out.println("Singly linked with stack: ");
    Node singlyLinked = Node.toSinglyLinkedList("tabat");
    System.out.println(singlyLinked);
    System.out.println("Is palindrome: " + isPalindromeWithStack(singlyLinked));

    singlyLinked = Node.toSinglyLinkedList("hello");
    System.out.println(singlyLinked);
    System.out.println("Is palindrome: " + isPalindromeWithStack(singlyLinked));
  }

  /**
   *  Checks if the provided singly linked list contains a palindrome.
   *  R:O(n), S:O(n).
   */
  static boolean isPalindromeWithStack(Node node) {
    Stack<Node> reverse = new Stack<Node>();
    Node toStack = node;
    while (toStack != null) {
      reverse.push(toStack);
      toStack = toStack.next;
    }
    while (node != null) {
      if (node.data != reverse.pop().data) return false;
      node = node.next;
    }
    return true;
  }

  /**
   *  Checks if the provided doubly linked list contains a palindrome.
   *  R:O(n), S:O(1).
   */
  static boolean isPalindrome(LinkedList<Character> list) {
    Iterator<Character> forward = list.iterator();
    Iterator<Character> backward = list.descendingIterator();

    while (forward.hasNext() && backward.hasNext() &&
           forward.next().equals(backward.next())) {}

    return !(forward.hasNext() || backward.hasNext());
  }

  static LinkedList<Character> toLinkedList(String word) {
    return word.chars().mapToObj(c -> (char)c).collect(Collectors.toCollection(LinkedList::new));
  }
}

