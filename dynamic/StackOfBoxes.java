import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

public class StackOfBoxes {
  public static void main(String[] args) {
    List<Box> boxes = new ArrayList<>();
    boxes.add(new Box(1,1,1));
    boxes.add(new Box(2,2,2));
    boxes.add(new Box(3,3,3));
    boxes.add(new Box(4,2,7));
    boxes.add(new Box(5,5,5));
    boxes.add(new Box(6,6,6));

    System.out.println("Table: " + tallestStack(boxes));
    System.out.println("Recursion: " + tallestStackRecursive(boxes));
  }

  static int tallestStackRecursive(List<Box> boxes) {
    Collections.sort(boxes, Comparator.comparing(Box::volume).reversed());

    List<Stack<Box>> stacks = new LinkedList<>();

    Map<Integer, List<Stack<Box>>> cache = new HashMap<>();
    for (int i = 0; i < boxes.size(); i++) {
      stacks.addAll(gatherStacks(boxes, i, cache));
    }

    return stacks.stream()
                 .mapToInt(stack -> stack.stream()
                                         .collect(Collectors.summingInt(Box::getHeight)))
                 .max()
                 .orElse(-1);
  }

  private static void print(List<Stack<Box>> stacks) {
    for (Stack<Box> stack : stacks) {
      System.out.println("**** Stack ****");
      for (Box box : stack) {
        System.out.println(box.height);
      }
      System.out.println("**** END Stack ****");
    }
  }

  private static List<Stack<Box>> gatherStacks(List<Box> boxes, int idx, Map<Integer, List<Stack<Box>>> cache) {
    if (cache.get(idx) != null) return cache.get(idx);

    Box bottom = boxes.get(idx);
    List<Stack<Box>> result = new LinkedList<>();
    for (int i = idx+1; i < boxes.size(); i++) {
      if (bottom.fits(boxes.get(i))) {
        List<Stack<Box>> children = gatherStacks(boxes, i, cache);
        for (Stack<Box> child : children) {
          Stack<Box> copy = new Stack<>();
          copy.addAll(child);
          copy.push(bottom);
          result.add(copy);
        }
      }
    }

    // last item
    if (result.isEmpty()) {
      Stack<Box> stack = new Stack<>();
      stack.push(bottom);
      result.add(stack);
    }

    cache.put(idx, result);
    return result;
  }

  static int tallestStack(List<Box> boxes) {
    Collections.sort(boxes, Comparator.comparing(Box::volume));   

    int[][] weights = new int[boxes.size()][boxes.size()];
    for (int row = 0; row < boxes.size(); row++) {
      for (int col = 0; col < boxes.size(); col++) {
        if (col == row) {
          weights[row][col] = -1;
          continue;
        }
        Box src = boxes.get(row);
        Box dst = boxes.get(col);
        if (dst.fits(src)) {
          weights[row][col] = dst.height + src.height;
        } else {
          weights[row][col] = -1;
        }
      }
    }
    for (int row = boxes.size() - 1; row >= 0; row--) {
      int max = max(weights, row);
      if (max < 0) continue;
      for (int innerRow = 0; innerRow < boxes.size(); innerRow++) {
        int weight = weights[innerRow][row];
        if (weight < 0) continue;
        weights[innerRow][row] = max + boxes.get(innerRow).height;
      }
    }

    int max = -1;
    for (int row = 0; row < boxes.size(); row++) {
      for (int col = 0; col < boxes.size(); col++) {
        int current = weights[row][col];
        if (max < current) max = current;
      }
    }
    return max;
  }

  static int max(int[][] weights, int row) {
    int max = -1;
    for (int i = 0; i < weights.length; i++)
      if (max < weights[row][i])
        max = weights[row][i];
    return max;
  }

  static class Box {
    int height;
    int width;
    int depth;

    Box(int height, int width, int depth) {
      this.height = height;
      this.width = width;
      this.depth = depth;
    }

    int getHeight() {
      return this.height;
    }

    int volume() {
      return this.height * this.width * this.depth;
    }

    boolean fits(Box other) {
      return other.height < this.height &&
             other.width < this.width &&
             other.depth < this.depth;
    }
  }
}

