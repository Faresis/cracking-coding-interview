import java.util.Stack;

public class TowersOfHanoi {
  private static Stack<Integer> a = new Stack<>();
  private static Stack<Integer> b = new Stack<>();
  private static Stack<Integer> c = new Stack<>();

  public static void main(String[] args) {
    a.push(4);
    a.push(3);
    a.push(2);
    a.push(1);
    System.out.println("A: " + a);
    System.out.println("B: " + b);
    System.out.println("C: " + c);

    System.out.println("Moving...");
    move(4, a, c, b);

    System.out.println("A: " + a);
    System.out.println("B: " + b);
    System.out.println("C: " + c);
  }

  static void move(Integer i, Stack<Integer> src, Stack<Integer> dst, Stack<Integer> buffer) {
    if (i == 0) return;
    move(i-1, src, buffer, dst);
    dst.push(src.pop());
    move(i-1, buffer, dst, src);
  }
}

