import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Arrays;

public class Fill {
  public static void main(String[] args) {
    Color[][] screen = createScreen();
    System.out.println("Before: ");
    print(screen);
    fill(screen, Point.of(3, 4), Color.Red);
    System.out.println("After: ");
    print(screen);
  }

  private static void print(Color[][] screen) {
    for (int i = 0; i < screen.length; i++) {
      System.out.println(Arrays.toString(screen[i]));
    }
  }

  private static Color[][] createScreen() {
    return new Color[][] {
      { Color.Green, Color.Green, Color.Green, Color.Green, Color.Green, Color.Green, Color.Green },
      { Color.Green, Color.Blue,  Color.Green, Color.Blue,  Color.Green, Color.Green, Color.Green },
      { Color.Green, Color.Blue,  Color.Green, Color.Blue,  Color.Blue,  Color.Blue,  Color.Green },
      { Color.Green, Color.Blue,  Color.Blue,  Color.Blue,  Color.Blue,  Color.Blue,  Color.Blue  },
      { Color.Green, Color.Green, Color.Blue,  Color.Green, Color.Blue,  Color.Blue,  Color.Green },
      { Color.Green, Color.Green, Color.Blue,  Color.Green, Color.Green, Color.Green, Color.Green }
    };
  }

  static void fill(Color[][] screen, Point point, Color newColor) {
    if (!inBounds(screen, point)) throw new IllegalArgumentException();

    Set<Point> points = new LinkedHashSet<Point>(List.of(point));
    Iterator<Point> it = points.iterator();
    while(it.hasNext()) {
      Point current = it.next();
      it.remove();
      Color oldColor = screen[current.x][current.y];
      screen[current.x][current.y] = newColor;
      points.addAll(getSameColorNeighbors(screen, current, oldColor));
      it = points.iterator();
    }
  }

  private static List<Point> getSameColorNeighbors(Color[][] screen, Point point, Color color) {
    return getAllPossibleNeighbors(point).stream()
                                         .filter(n -> inBounds(screen, n))
                                         .filter(n -> screen[n.x][n.y] == color)
                                         .collect(toList());
  }

  private static boolean inBounds(Color[][] screen, Point point) {
    return screen.length > point.x && screen[point.x].length > point.y;
  }

  private static List<Point> getAllPossibleNeighbors(Point p) {
    return List.of(
      Point.of(p.x-1, p.y-1),
      Point.of(p.x-1, p.y),
      Point.of(p.x, p.y-1),
      Point.of(p.x+1, p.y+1),
      Point.of(p.x+1, p.y),
      Point.of(p.x, p.y+1),
      Point.of(p.x-1, p.y+1),
      Point.of(p.x+1, p.y-1)
    );
  }

  enum Color { Red, Green, Blue }

  static class Point {
    int x;
    int y;

    static Point of(int x, int y) {
      return new Point(x, y);
    }

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "{ " + x + ", " + y + " }";
    }

    @Override
    public int hashCode() {
      return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
      if (other == null) return false;
      if (other instanceof Point) {
        return other.toString().equals(this.toString());
      }
      return false;
    }
  }
}
