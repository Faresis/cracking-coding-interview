import java.util.Arrays;

public class Queens {
  private static int count = 0;
  public static void main(String[] args) {
    printCombinations(new boolean[8][8]);
    System.out.println(count);
  }

  static void printCombinations(boolean[][] board) {
    boolean[] usedColumns = new boolean[board[0].length];
    printCombinations(board, board.length - 1, usedColumns);
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
}

