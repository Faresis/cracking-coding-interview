// rotate matrix solution from the book
public class RotateMatrixBook {
  static void rotateMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix.length != matrix[0].length)
      throw new IllegalArgumentException();

    int n = matrix.length;
    for (int layer = 0; layer < n/2; layer++) {
      int first = layer;
      int last = n - 1 - layer;
      for (int i = first; i < last; i++) {
        int offset = i - first;

        int top = matrix[first][i];

        // left -> top
        matrix[first][i] = matrix[last-offset][first];
        // bottom -> left
        matrix[last-offset][first] = matrix[last][last-offset];
        // right -> bottom
        matrix[last][last-offset] = matrix[i][last];
        // top -> right
        matrix[i][last] = top;
      }
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {1,2,3},
      {4,5,6},
      {7,8,9}
    };
    printMatrix(matrix);
    rotateMatrix(matrix);
    printMatrix(matrix);

    matrix = new int[][] {
      {1,2,3,4,5},
      {6,7,8,9,10},
      {11,12,13,14,15},
      {16,17,18,19,20},
      {21,22,23,24,25}
    };
    printMatrix(matrix);
    rotateMatrix(matrix);
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
    rotateMatrix(matrix);
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

