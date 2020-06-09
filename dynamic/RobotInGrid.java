import java.util.List;
import java.util.ArrayList;

public class RobotInGrid {
  public static void main(String[] args) {
    Grid grid = new Grid(8, 8);
    grid.cells[0][0].isRobot = true;
    grid.cells[7][1].isBlocked = true;
    grid.cells[6][1].isBlocked = true;
    grid.cells[5][1].isBlocked = true;
    grid.cells[4][1].isBlocked = true;
    grid.cells[3][1].isBlocked = true;
    grid.cells[3][2].isBlocked = true;
    grid.cells[3][3].isBlocked = true;
    grid.cells[3][4].isBlocked = true;
    grid.cells[3][5].isBlocked = true;
    grid.cells[3][6].isBlocked = true;

    List<Path> path = findPath(grid, 7, 7);
    System.out.println(path);
  }

  static List<Path> findPath(Grid grid, int row, int col) {
    Cell curr = grid.get(row, col);
    if (curr == null || curr.isBlocked) return List.of();
    Cell left = grid.get(row, col-1);
    if (left != null && left.isRobot) return List.of(Path.RIGHT);
    Cell up = grid.get(row-1, col);
    if (up != null && up.isRobot) return List.of(Path.DOWN);
    List<Path> leftPath = findPath(grid, row, col-1);
    if (!leftPath.isEmpty()) {
      return new ArrayList<Path>() {{
        addAll(leftPath);
        add(Path.RIGHT);
      }};
    }
    List<Path> upPath = findPath(grid, row-1, col);
    if (!upPath.isEmpty()) {
      return new ArrayList<Path>() {{
        addAll(upPath);
        add(Path.DOWN);
      }};
    }
    return List.of();
  }

  enum Path { RIGHT, DOWN }

  static class Grid {
    Cell[][] cells;

    Cell get(int row, int col) {
      if (row < 0 || row >= cells.length) return null;
      if (col < 0 || col >= cells.length) return null;
      return cells[row][col];
    }

    Grid(int rows, int cols) {
      cells = new Cell[rows][cols];
      for (int row = 0; row < rows; row++)
        for (int col = 0; col < cols; col++)
          cells[row][col] = new Cell();
    }
  }

  static class Cell {
    boolean isRobot;
    boolean isBlocked;
  }
}

