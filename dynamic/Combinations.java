import java.util.List;

public class Combinations {
  public static void main(String[] args) {
    for (int i = 1; i < 26; i++) {
      System.out.println("Combinations TD(" + i + "): " + combinationsTopDown(i, List.of(1,5,10,25)));
    }
  }

  static int combinationsTopDown(int num, List<Integer> elements) {
    if (num == 0) return 1;
    if (elements.isEmpty()) return 0;
    if (num < 0) return 0;

    return combinationsTopDown(num - elements.get(0), elements) +
           combinationsTopDown(num, elements.subList(1, elements.size()));   
  }
}

