import java.util.*;

class RotateMatrix {
  static void rotateMatrix(int[][] arr, int len) {
    int start = 0;
    len = len - 1;
    int shift = len;
    
    while (len > 1) {
      for (int i = start; i < start+len; i++) {
        rotateCell(start, i, shift, arr);
      }
      start++;
      len -= 2;
    }
  }

  private static void rotateCell(int row, int col, int shift, int[][] arr) {
    int val = arr[row][col];
    for (int i = 0; i < 4; i++) {
      int tmpRow = row;
      row = col;
      col = shift - tmpRow;
      int tmpVal = val;
      val = arr[row][col];
      arr[row][col] = tmpVal;
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {1,2,3},
      {4,5,6},
      {7,8,9}
    };
    printMatrix(matrix);
    rotateMatrix(matrix, 3);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,4,5},
      {6,7,8,9,10},
      {11,12,13,14,15},
      {16,17,18,19,20},
      {21,22,23,24,25}
    };
    printMatrix(matrix);
    rotateMatrix(matrix, 5);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,4,5,6,7},
      {8,9,10,11,12,13,14},
      {15,16,17,18,19,20,21},
      {22,23,24,25,26,27,28},
      {29,30,31,32,33,34,35},
      {36,37,38,39,40,41,42},
      {43,44,45,46,47,48,49}
    };
    printMatrix(matrix);
    rotateMatrix(matrix, 7);
    printMatrix(matrix);
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
}
