import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Arrays;

/**
 *  Remove duplicates: Write code to remvoe duplicates from an unsorted linked list.
 */
public class RemoveDuplicates {
  public static void main(String[] args) {
    LinkedList<Character> list = new LinkedList<>();
    list.addAll(Arrays.asList('h','e','l','l','o'));

    System.out.println(list);
    removeDuplicatesWithSet(list);
    System.out.println(list);
  }

  /**
   * Removes duplicates using a hash set. R:O(n), S:O(n).
   */
  static void removeDuplicatesWithSet(LinkedList<Character> list) {
    Set<Character> filter = new HashSet<>();
    Iterator<Character> iterator = list.iterator();
    while(iterator.hasNext()) {
      if (!filter.add(iterator.next())) {
        iterator.remove();
      }
    }
  }
}
