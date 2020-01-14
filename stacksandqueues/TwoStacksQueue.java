import java.util.Stack;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

class MyQueueLazyRemoval {
  Stack<Integer> newestFirst = new Stack<>();
  Stack<Integer> oldestFirst = new Stack<>();

  void add(int data) {
    this.newestFirst.push(data);
  }

  int remove() {
    if (oldestFirst.isEmpty()) moveAll(newestFirst, oldestFirst);
    return oldestFirst.pop();
  }

  boolean isEmpty() {
    return oldestFirst.isEmpty() && newestFirst.isEmpty();
  }

  private static <T> void moveAll(Stack<? extends T> from, Stack<? super T> to) {
    while (!from.empty()) {
      to.push(from.pop());
    }
  }
}

class MyQueueFastRemoval {
  Stack<Integer> s1 = new Stack<>();
  Stack<Integer> s2 = new Stack<>();

  void add(int data) {
    moveAll(s1, s2);
    s1.push(data);
    moveAll(s2, s1);
  }

  int remove() {
    return s1.pop();
  }

  boolean isEmpty() {
    return s1.empty();
  }

  private static <T> void moveAll(Stack<? extends T> from, Stack<? super T> to) {
    while (!from.empty()) {
      to.push(from.pop());
    }
  }
}

class MyQueueFastAdd {
  Stack<Integer> s1 = new Stack<>();
  Stack<Integer> s2 = new Stack<>();

  void add(int data) {
    s1.push(data);
  }

  int remove() {
    moveAll(s1, s2);
    int res = s2.pop();
    moveAll(s2, s1);
    return res;
  }

  List<Integer> remove(int num){
    moveAll(s1, s2);
    List<Integer> res = new LinkedList<Integer>();
    while (num-- > 0) {
      res.add(s2.pop());
    }
    moveAll(s2, s1);
    return res;
  }

  boolean isEmpty() {
    return s1.empty();
  }

  private static <T> void moveAll(Stack<? extends T> from, Stack<? super T> to) {
    while (!from.empty()) {
      to.push(from.pop());
    }
  }
}

public class TwoStacksQueue {
  public static void main(String[] args) {
    System.out.println("Fast removal.");
    MyQueueFastRemoval qr = new MyQueueFastRemoval();
    qr.add(1);
    qr.add(2);
    qr.add(3);
    qr.add(4);
    qr.add(5);
    qr.add(6);
    qr.add(7);
    qr.add(8);
    qr.add(9);
    qr.add(10);

    while (!qr.isEmpty())
      System.out.println(qr.remove());

    System.out.println("Fast add.");
    MyQueueFastAdd qa = new MyQueueFastAdd();
    qa.add(1);
    qa.add(2);
    qa.add(3);
    qa.add(4);
    qa.add(5);
    qa.add(6);
    qa.add(7);
    qa.add(8);
    qa.add(9);
    qa.add(10);

    System.out.println("Removing three.");
    System.out.println(qa.remove(3));
    System.out.println("Removing all.");
    while (!qa.isEmpty())
      System.out.println(qa.remove());

    System.out.println("Lazy removal.");
    MyQueueLazyRemoval lr = new MyQueueLazyRemoval();
    lr.add(1);
    lr.add(2);
    lr.add(3);
    lr.add(4);
    lr.add(5);
    lr.add(6);
    lr.add(7);
    lr.add(8);
    lr.add(9);
    lr.add(10);

    while (!lr.isEmpty())
      System.out.println(lr.remove());
  }
}

