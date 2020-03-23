public class DrawLine {
  public static void main(String[] args) {
    int width = 24;
    byte[] screen = new byte[30];
    print(screen, width); // clear screen
    drawLine(screen, width, 3, 9, 5);
    print(screen, width); // row with idx 5 (zero based) with bits 3 to 9 inclusively (zero based) are set to 1s

    System.out.println("Single pixel scenario.");
    screen = new byte[30];
    print(screen, width); // clear screen
    drawLine(screen, width, 3, 3, 5);
    print(screen, width);

    System.out.println("Single pixel scenario. Revised.");
    screen = new byte[30];
    print(screen, width); // clear screen
    drawLineRevised(screen, width, 3, 3, 5);
    print(screen, width);

    System.out.println("Middle full pixel scenario.");
    screen = new byte[30];
    print(screen, width);
    drawLine(screen, width, 3, 17, 7);
    print(screen, width);

    System.out.println("Middle full pixel scenario. Revised.");
    screen = new byte[30];
    print(screen, width);
    drawLineRevised(screen, width, 3, 17, 7);
    print(screen, width);
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

  private static void drawLineRevised(byte[] screen, int width, int x1, int x2, int y) {
    int start_offset = x1 % 8;
    int first_full_byte = x1 / 8;
    if (start_offset != 0) first_full_byte++;

    int end_offset = x2 % 8;
    int last_full_byte = x2 / 8;
    if (end_offset != 7) last_full_byte--;

    for (int i = first_full_byte; i <= last_full_byte; i++) {
      screen[width/8*y+i] = (byte) 0xFF;
    }

    byte start_mask = (byte) (0xFF >> start_offset);
    byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

    if ((x1/8) == (x2/8)) {
      byte mask = (byte) (start_mask & end_mask);
      screen[width/8*y + x1/8] |= mask;
    } else {
      if (start_offset != 0) {
        int byteIdx = width/8*y + first_full_byte - 1;
        screen[byteIdx] |= start_mask;
      }
      if (end_offset != 7) {
        int byte_number = width/8*y + last_full_byte + 1;
        screen[byte_number] |= end_mask;
      }
    }
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
    for (int i = start; i <= end; i++) {
      // full byte optimization
      if (i%8 == 0 && i+7 <= end) {
        result[i/8] = (byte) 0xFF;
        i = i+7;
        continue;
      }
      result[i/8] |= (1 << (7-(i%8)));
    }
    return result;
  }
}

