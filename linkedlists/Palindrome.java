import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 *  Palindrome: Implement a function to check if a linked list is a palindrome.
 */
public class Palindrome {
  public static void main(String[] args) {
    LinkedList<Character> doublyLinked = toLinkedList("tabat");
    System.out.println(doublyLinked);
    System.out.println("Is palindrome: " + isPalindrome(doublyLinked));

    doublyLinked = toLinkedList("hello");
    System.out.println(doublyLinked);
    System.out.println("Is palindrome: " + isPalindrome(doublyLinked));
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

