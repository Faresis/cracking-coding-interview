import java.util.Stack;

class StackWithMin extends Stack<Integer> {
  Stack<Integer> mins = new Stack<>();

  public void push(int value) {
    if (value < this.min()) mins.push(value);
    super.push(value);
  }

  public Integer pop() {
    Integer value = super.pop();
    if (value == min()) mins.pop();
    return value;
  }

  public int min() {
    return mins.isEmpty() ? Integer.MAX_VALUE : mins.peek();
  }
}

public class StackMinBook {
  public static void main(String[] args) {
    StackWithMin s = new StackWithMin();
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

