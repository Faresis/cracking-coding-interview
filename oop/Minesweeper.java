import java.util.Optional;
import java.util.Scanner;

public class Minesweeper {
  private static class Commands {
    static final String EXIT = "E";
    static final String PRINT = "P";
    static final String OPEN = "O";
    static final String FLAG = "F";
    static final String UNFLAG = "U";

    static void print() {
      System.out.printf("\nE - to exit,\n" +
                        "P - to print,\n" +
                        "O - to open,\n" +
                        "F - to flag,\n" +
                        "U - to unflag,\n" +
                        "Enter a command:\n");
    }
  }

  public static void main(String[] args) {
    Grid grid = create();
    grid.print();
    while (true) {
      Commands.print();
      Scanner in = new Scanner(System.in);
      String command = in.nextLine();
      if (Commands.EXIT.equals(command)) {
        System.out.println("Exiting.");
        return;
      }
      if (Commands.PRINT.equals(command)) {
        grid.print();
      }
      if (Commands.OPEN.equals(command)) {
        System.out.println("Enter a row number: ");
        int row = in.nextInt();
        System.out.println("Enter a column number: ");
        int column = in.nextInt();
        grid.open(row, column);
        grid.print();
        if (grid.isWon()) {
          System.out.println("Congratulations you won!");
          return;
        }
        if (grid.isLost()) {
          System.out.println("Booom! You lost!");
          return;
        }
      }
      if (Commands.FLAG.equals(command)) {
        System.out.println("Enter a row number: ");
        int row = in.nextInt();
        System.out.println("Enter a column number: ");
        int column = in.nextInt();
        grid.flag(row, column);
        grid.print();
      }
      if (Commands.UNFLAG.equals(command)) {
        System.out.println("Enter a row number: ");
        int row = in.nextInt();
        System.out.println("Enter a column number: ");
        int column = in.nextInt();
        grid.unflag(row, column);
        grid.print();
      }
    }
  }

  private static Grid create() {
    int size = 7;
    Grid grid = new Grid(size);
    // 0111000
    grid.setHint(0, 1, 1);
    grid.setHint(0, 2, 1);
    grid.setHint(0, 3, 1);
    // 01B1000
    grid.setHint(1, 1, 1);
    grid.setBomb(1, 2);
    grid.setHint(1, 3, 1);
    // 0222000
    grid.setHint(2, 1, 2);
    grid.setHint(2, 2, 2);
    grid.setHint(2, 3, 2);
    // 01B1000
    grid.setHint(3, 1, 1);
    grid.setBomb(3, 2);
    grid.setHint(3, 3, 1);
    // 0111000
    grid.setHint(4, 1, 1);
    grid.setHint(4, 2, 1);
    grid.setHint(4, 3, 1);
    // 0001110
    grid.setHint(5, 3, 1);
    grid.setHint(5, 4, 1);
    grid.setHint(5, 5, 1);
    // 0001B1
    grid.setHint(6, 3, 1);
    grid.setBomb(6, 4);
    grid.setHint(6, 5, 1);
    return grid;
  }
}

class Cell {
  boolean isOpen;
  Optional<Integer> hint = Optional.empty();
  boolean isBomb;
  boolean isFlagged;

  @Override
  public String toString() {
    if (isFlagged) return "F";
    if (!isOpen) return "X";
    if (isBomb) return "B";
    if (hint.isPresent()) return String.valueOf(hint.get());
    return "O";
  }
}

class Grid {
  final Cell[][] cells;
  State state = State.NONE;

  Grid(int size) {
    cells = new Cell[size][size];
    for (int row = 0; row < size; row++)
      for (int col = 0; col < size; col++)
        cells[row][col] = new Cell();
  }

  void openAll() {
    for (int row = 0; row < cells.length; row++)
      for (int col = 0; col < cells[row].length; col++)
        cells[row][col].isOpen = true;
  }

  void setBomb(int row, int col) {
    check(row, col);

    cells[row][col].isBomb = true;
  }

  void setHint(int row, int col, int hint) {
    check(row, col);

    cells[row][col].hint = Optional.of(hint);
  }

  void flag(int row, int col) {
    check(row, col);

    cells[row][col].isFlagged = true;
  }

  void unflag(int row, int col) {
    check(row, col);

    cells[row][col].isFlagged = false;
  }

  void open(int row, int col) {
    check(row, col);

    Cell cell = cells[row][col];
    if (cell.isFlagged) return;
    
    if (cell.isBomb) {
      cell.isOpen = true;
      this.state = State.LOST;
      return;
    }
    expose(row, col);
    checkWin();
  }

  boolean isWon() {
    return this.state == State.WON;
  }

  boolean isLost() {
    return this.state == State.LOST;
  }

  private void expose(int row, int col) {
    if (row < 0 || row >= cells.length || col < 0 || col >= cells[row].length) return;
    Cell cell = cells[row][col];

    if (cell.isOpen) return;
    if (cell.isBomb) return;

    cell.isOpen = true;
    if (cell.hint.isPresent()) return;
    
    for (int childRow = row-1; childRow <= row+1; childRow++)
      for (int childCol = col-1; childCol <= col+1; childCol++)
        expose(childRow, childCol);
  }

  private void checkWin() {
    for (int row = 0; row < cells.length; row++)
      for (int col = 0; col < cells[row].length; col++)
        if (!cells[row][col].isOpen && !cells[row][col].isBomb)
          return;
    this.state = State.WON;
  }

  void print() {
    for (int row = 0; row < cells.length; row++) {
      for (int col = 0; col < cells[row].length; col++) {
        Cell cell = cells[row][col];
        System.out.printf("%s", cell);
      }
      System.out.println();
    }
  }

  private void check(int row, int col) {
    if (this.state == State.LOST || this.state == State.WON)
      throw new IllegalStateException();
    if (row < 0 || row >= cells.length)
      throw new IndexOutOfBoundsException("Row is out of bounds. " + row);
    if (col < 0 || col >= cells[row].length)
      throw new IndexOutOfBoundsException("Col is out of bounds. " + col);
  }
}

enum State {
  NONE, WON, LOST
}

