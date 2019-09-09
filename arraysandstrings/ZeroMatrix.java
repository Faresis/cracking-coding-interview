class ZeroMatrix {
  static void zeroMatrix(int[][] matrix, int rows, int cols) {
    boolean clearFirstRow = false;
    boolean clearFirstCol = false;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (matrix[row][col] == 0) {
           if (row == 0) clearFirstRow = true;
           if (col == 0) clearFirstCol = true;
           matrix[row][0] = 0;
           matrix[0][col] = 0;
        }
      }
    }
    for (int row = 1; row < rows; row++) {
      if (matrix[row][0] == 0) setRowValue(matrix, row, cols, 0);
    }
    for (int col = 1; col < cols; col++) {
      if (matrix[0][col] == 0) setColValue(matrix, col, rows, 0);
    }
    if (clearFirstRow) setRowValue(matrix, 0, cols, 0);
    if (clearFirstCol) setColValue(matrix, 0, rows, 0);
  }

  private static void setRowValue(int[][] arr, int row, int cols, int val) {
    for (int col = 0; col < cols; col++) {
      arr[row][col] = val;
    }
  }

  private static void setColValue(int[][] arr, int col, int rows, int val) {
    for (int row = 0; row < rows; row++) {
      arr[row][col] = val;
    }
  }

  private static void printMatrix(int[][] matrix) {
    for (int[] arr : matrix) {
      for (int i : arr) {
        System.out.printf("%d\t", i);
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {1,2,3,4,5},
      {1,2,3,4,5},
      {1,2,3,0,5},
      {1,2,3,4,5}
    };
    printMatrix(matrix);
    zeroMatrix(matrix, 4, 5);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,4,5},
      {1,2,3,4,5},
      {0,2,3,4,5},
      {1,2,3,4,5}
    };
    printMatrix(matrix);
    zeroMatrix(matrix, 4, 5);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,0,5},
      {1,2,3,4,5},
      {1,2,3,4,5},
      {1,2,3,4,5}
    };
    printMatrix(matrix);
    zeroMatrix(matrix, 4, 5);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,4,0},
      {1,2,3,4,5},
      {1,2,3,4,5},
      {1,2,3,4,5}
    };
    printMatrix(matrix);
    zeroMatrix(matrix, 4, 5);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,4,5},
      {1,2,3,4,5},
      {1,2,3,4,0},
      {1,2,3,4,5}
    };
    printMatrix(matrix);
    zeroMatrix(matrix, 4, 5);
    printMatrix(matrix);

    matrix = new int[][] {
      {0,2,3,4,5},
      {1,2,3,4,5},
      {1,2,3,4,5},
      {1,2,3,4,5}
    };
    printMatrix(matrix);
    zeroMatrix(matrix, 4, 5);
    printMatrix(matrix);
  }
}
