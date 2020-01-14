import java.util.Stack;

public class SortStack {
  public static void sort(Stack<Integer> stack) {
    Stack<Integer> tmp = new Stack<>();
    while (!stack.isEmpty()) {
      int curr = stack.pop();
      if (tmp.isEmpty() || curr >= tmp.peek()) {
        tmp.push(curr);
      } else {
        stack.push(tmp.pop());
        stack.push(curr);
      }
    }
    while (!tmp.isEmpty()) stack.push(tmp.pop());
  }

  public static void main(String[] args) {
    Stack<Integer> s = new Stack<>();
    s.push(7);
    s.push(1);
    s.push(3);
    s.push(5);
    s.push(2);
    s.push(4);
    s.push(6);
    s.push(9);
    s.push(8);
    s.push(10);

    System.out.println(s);
    sort(s);
    System.out.println(s);
    while (!s.isEmpty())
      System.out.println(s.pop());
  }
}

