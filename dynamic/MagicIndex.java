import java.util.Arrays;

public class MagicIndex {
  public static void main(String[] args) {
    int size = 100;
    int[] upper = new int[size];
    for (int i = 0; i < size; i++)
      upper[i] = i-1;
    upper[99] = 99;
    System.out.println("Upper: " + Arrays.toString(upper));
    System.out.println("Index: " + magicIndex(upper, 0, size));

    int[] lower = new int[size];
    for (int i = 0; i < size; i++)
      lower[i] = i+1;
    lower[0] = 0;
    System.out.println("Lower: " + Arrays.toString(lower));
    System.out.println("Index: " + magicIndex(lower, 0, size));

    int[] empty = new int[size];
    for (int i = 0; i < size; i++)
      empty[i] = i+1;
    System.out.println("Empty: " + Arrays.toString(empty));
    System.out.println("Index: " + magicIndex(empty, 0, size));

    int[] mixed = new int[size];
    for (int i = 0; i < size; i++)
      mixed[i] = i > 77 ? i+1 : i-1;
    mixed[77] = 77;
    System.out.println("Mixed: " + Arrays.toString(mixed));
    System.out.println("Index: " + magicIndex(mixed, 0, size));
  }

  static int magicIndex(int[] arr, int start, int end) {
    if (start > end) return -1;
    int mid = (start + end)/2;
    if (arr[mid] == mid) return mid;
    else if(arr[mid] < mid) return magicIndex(arr, mid+1, end); 
    // arr[mid] > mid
    else return magicIndex(arr, start, mid-1);
  }
}

