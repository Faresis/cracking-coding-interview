class UrlifyString {
  private static final char SEPARATOR = ' ';
  private static final char PERCENT= '%';
  private static final int MOVE = 2;

  static void urlify(char[] string, int len) {
    int spacesCount = countSpaces(string, len);
    int shift = spacesCount * MOVE;
    for (int i = len-1; i >= 0; i--) {
      if (string[i] == SEPARATOR) {
        shift -= MOVE;
        string[i+shift] = PERCENT;
        string[i+shift+1] = '2';
        string[i+shift+2] = '0';
      } else {
        string[i+shift] = string[i];
      }
    }
  }

  private static int countSpaces(char[] string, int len) {
    int count = 0;
    for (int i = 0; i < len; i++)
      if (string[i] == SEPARATOR)
        count++;
    return count;
  }

  public static void main(String[] args) {
    char[] text = "Mr John Smith".toCharArray();
    char[] string = new char[17];
    System.arraycopy(text, 0, string, 0, text.length);

    urlify(string, 13);

    System.out.println(new String(string));
  }
}

