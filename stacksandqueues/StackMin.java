import java.util.EmptyStackException;

public class StackMin {
  private static class StackNode {
    StackNode prev;
    int data;
    int min;
    
    StackNode(int data, int min) {
      this.data = data;
      this.min = min;
    }
  }

  StackNode top;

  void push(int data) {
    if (this.top == null) {
      this.top = new StackNode(data, data);
    } else {
      StackNode n = new StackNode(data, data < this.top.min ? data : this.top.min);
      n.prev = this.top;
      this.top = n;
    }
  }

  int pop() {
    if (this.top == null) throw new EmptyStackException();
    int result = this.top.data;
    this.top = this.top.prev;
    return result;
  }

  int min() {
    if (this.top == null) throw new EmptyStackException();
    return this.top.min;
  }

  boolean isEmpty() {
    return this.top == null;
  }

  public static void main(String[] args) {
    StackMin s = new StackMin();
    s.push(8);
    s.push(5);
    s.push(9);
    s.push(7);
    s.push(3);
    s.push(6);
    s.push(2);
    s.push(1);

    while (!s.isEmpty()) {
      System.out.println("Min: " + s.min() + " Data: " + s.pop());
    }
  }
}

