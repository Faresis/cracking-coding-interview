public class DrawLine {
  public static void main(String[] args) {
    int width = 24;
    byte[] screen = new byte[30];
    print(screen, width); // clear screen
    drawLine(screen, width, 3, 9, 5);
    print(screen, width); // row with idx 5 (zero based) with bits 3 to 9 inclusively (zero based) are set to 1s
  }

  private static void print(byte[] screen, int width) {
    int row = width / 8;
    System.out.println("Screen start: ");
    for (int i = 0; i < screen.length; i++) {
      if (i % row == 0)
        System.out.println();
      System.out.print(String.format("%8s", Integer.toBinaryString(screen[i] & 0xFF)).replace(' ', '0'));
    }
    System.out.println("\nScreen end: ");
  }

  private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
    int rowLength = width / 8;
    int height = screen.length / rowLength;
    int rowIdx = height - y - 1;
    // rowMask expected to be of rowLength length.
    byte[] rowMask = createRowMask(x1, x2, width);
    int start = rowIdx * rowLength;
    for (int cell = 0; cell < rowLength; cell++)
      screen[start+cell] |= rowMask[cell];
  }

  private static byte[] createRowMask(int start, int end, int width) {
    int len = width / 8;
    byte[] result = new byte[len];
    for (int i = start; i <= end; i++)
      result[i/8] |= (1 << (7-(i%8)));
    return result;
  }
}

