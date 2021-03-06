import java.util.LinkedList;
import java.util.Iterator;
import java.util.EmptyStackException;

public class ListOfStacks {
  private LinkedList<Stack> stacks = new LinkedList<>();
  private final int threshold;

  ListOfStacks(int threshold) {
    this.threshold = threshold;
  }

  public void push(int data) {
    Stack targetStack = null;
    Iterator<Stack> it = stacks.descendingIterator();
    if (it.hasNext()) {
      Stack top = it.next();
      if (!top.isFull()) {
        targetStack = top;
      }
    }
    if (targetStack == null) {
      Stack newStack = new Stack(threshold);
      stacks.add(newStack);
      targetStack = newStack;
    }
    targetStack.push(data);
  }

  public int pop() {
    Iterator<Stack> it = stacks.descendingIterator();
    while (it.hasNext()) {
      Stack top = it.next();
      if (top.isEmpty()) {
        it.remove();
      } else {
        int result = top.pop();
        if (top.isEmpty()) it.remove();
        return result;
      }
    }
    throw new EmptyStackException();
  }

  public boolean isEmpty() {
    return !this.stacks.iterator().hasNext();
  }

  public int popAt(int idx) {
    if (stacks.size() <= idx) throw new ArrayIndexOutOfBoundsException();
    Stack stack = stacks.get(idx);
    int result = stack.pop();
    if (stack.isEmpty()) stacks.remove(idx);
    return result;
  }

  public void print() {
    for (int i = 0; i < stacks.size(); i++) {
      System.out.printf("[%d]: %s\n", i, stacks.get(i));
    }
  }

  private static class Stack {
    private static class StackNode {
      StackNode prev;
      int data;
    
      StackNode(int data) {
        this.data = data;
      }
    }

    private StackNode top;
    private int size;
    private final int threshold;

    Stack(int threshold) {
      this.threshold = threshold;
    }

    void push(int data) {
      if (this.isFull()) throw new IllegalStateException();
      StackNode n = new StackNode(data);
      n.prev = this.top;
      this.top = n;
      this.size++;
    }
 
    int pop() {
      if (this.top == null) throw new EmptyStackException();
      int result = this.top.data;
      this.top = this.top.prev;
      this.size--;
      return result;
    }

    boolean isEmpty() {
      return this.top == null;
    }

    boolean isFull() {
      return this.threshold == this.size;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      StackNode curr = this.top;
      while (curr != null) {
        sb.append(curr.data);
        sb.append(", ");
        curr = curr.prev;
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    ListOfStacks ls = new ListOfStacks(3);

    System.out.println("Full stack.");
    add(ls, 10);
    ls.print();

    System.out.println("Clearing stack.");
    while (!ls.isEmpty())
      System.out.println(ls.pop());
    ls.print();

    System.out.println("Full stack.");
    add(ls, 10);
    ls.print();


    System.out.println("Removing from the middle.");
    System.out.println(ls.popAt(1));
    System.out.println(ls.popAt(1));
    System.out.println(ls.popAt(1));
    ls.print();

    System.out.println("Clearing stack.");
    while (!ls.isEmpty())
      System.out.println(ls.pop());
    ls.print();
  }

  private static void add(ListOfStacks ls, int num) {
    for (int i = 1; i <= num; i++)
      ls.push(i);
  }
}

