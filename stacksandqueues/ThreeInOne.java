import java.util.EmptyStackException;
import java.util.Arrays;

class Stack {
  private final int[] arr;
  private final int start;
  private final int size;
  private int head;

  Stack(int[] arr, int start, int size) {
    this.arr = arr;
    this.start = start;
    this.size = size;
    this.head = start - 1;
  }

  void push(int data) {
    int newHead = this.head + 1;
    if (newHead >= start+size) throw new StackOverflowError();
    this.arr[newHead] = data;
    this.head = newHead;
  }

  int pop() {
    if (head < start) throw new EmptyStackException();
    return this.arr[this.head--];
  }

  boolean isEmpty() {
    return head < start;
  }
}

public class ThreeInOne {
  public static void main(String[] args) {
    int[] arr = new int[10];
    Stack s1 = new Stack(arr, 0, 3);
    Stack s2 = new Stack(arr, 3, 3);
    Stack s3 = new Stack(arr, 6, 4);

    s1.push(1);
    s1.push(1);
    s1.push(1);
    s2.push(2);
    s2.push(2);
    s2.push(2);
    s3.push(3);
    s3.push(3);
    s3.push(3);
    s3.push(3);

    System.out.println(Arrays.toString(arr));
    while (!s1.isEmpty()) {
      System.out.println(s1.pop());
    }
    while (!s2.isEmpty()) {
      System.out.println(s2.pop());
    }
    while (!s3.isEmpty()) {
      System.out.println(s3.pop());
    }
    System.out.println(Arrays.toString(arr));
  }
}

