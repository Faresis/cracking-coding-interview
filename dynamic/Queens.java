import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Queens {
  private static int count = 0;
  public static void main(String[] args) {
    System.out.println("Checking the end.");
    printCombinations(new boolean[8][8]);
    System.out.println(count);

    System.out.println("Checking on the run.");
    List<Integer[]> combinations = generateCombinations(8);
    print(combinations);
    System.out.println(combinations.size());
  }

  static void printCombinations(boolean[][] board) {
    boolean[] usedColumns = new boolean[board[0].length];
    printCombinations(board, board.length - 1, usedColumns);
  }

  static List<Integer[]> generateCombinations(int gridSize) {
    List<Integer[]> results = new LinkedList<>();
    Integer[] grid = new Integer[gridSize];
    generateCombinations(gridSize, 0, grid, results);
    return results;
  }

  static void generateCombinations(int gridSize, int row, Integer[] grid, List<Integer[]> results) {
    if (row == gridSize) {
      results.add(grid.clone());
      return;
    }

    for (int column = 0; column < gridSize; column++) {
      if (checkValid(grid, row, column)) {
        grid[row] = column;
        generateCombinations(gridSize, row+1, grid, results);
      }
    }
  }

  private static boolean checkValid(Integer[] grid, int row, int column) {
    for (int previousRow = 0; previousRow < row; previousRow++) {
      int previousColumn = grid[previousRow];
      
      if (previousColumn == column) return false;

      int columnsDiff = Math.abs(previousColumn - column);
      int rowsDiff = row - previousRow;

      if (columnsDiff == rowsDiff) return false;
    }
    return true;
  }

  static void printCombinations(boolean[][] board, int row, boolean[] usedColumns) {
    if (row < 0) {
      if (checkDiagonals(board)) print(board);
      return;
    }

    for (int col = 0; col < board[row].length; col++) {
      if (usedColumns[col]) continue;
      board[row][col] = true;
      usedColumns[col] = true;
      printCombinations(board, row - 1, usedColumns);
      board[row][col] = false;
      usedColumns[col] = false;
    }
  }

  private static boolean checkDiagonals(boolean[][] board) {
    for (int i = 1; i < board[0].length; i++) {
      int col = i;
      int row = 0;
      int countLeftTop = 0;
      int countRightBottom = 0;
      int countLeftBottom = 0;
      int countRightTop = 0;
      int last = board.length - 1;
      while (col >= 0) {
        if (board[row][col]) countLeftTop++;
        if (board[last-row][last-col]) countRightBottom++;
        if (board[last-row][col]) countLeftBottom++;
        if (board[row][last-col]) countRightTop++;

        row++;
        col--;

        if (countLeftTop > 1 || countRightBottom > 1 || countLeftBottom > 1 || countRightTop > 1) {
          return false;
        }
      }
    }
    return true;
  }

  private static void print(boolean[][] board) {
    count++;
    System.out.println("******** Board ********");
    for (int i = 0; i < board.length; i++)
      System.out.println(Arrays.toString(board[i]));
    System.out.println("******** END Board ********");
  }

  private static void print(List<Integer[]> boards) {
    for (Integer[] board : boards) {
      System.out.println("******** Board ********");
      for (int row = 0; row < board.length; row++) {
        int queen = board[row];
        System.out.print("[");
        for (int column = 0; column < board.length; column++) {
          if (column == queen)
            System.out.print("X");
          else
            System.out.print("O");
        }
        System.out.print("]\n");
      }
      System.out.println("******** END Board ********");
    }
  }
}

