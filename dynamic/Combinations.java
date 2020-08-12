import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Combinations {
  public static void main(String[] args) {
    for (int i = 1; i < 100; i++) {
      System.out.println("Combinations TD(" + i + "): " + combinationsTopDown(i, new int[] { 25,10,5,1 }));
      System.out.println("Combinations BU(" + i + "): " + combinationsBottomUp(i, List.of(1,5,10,25)));
    }

    // -Xmx8192m -XX:MaxPermSize=6144m 
    System.out.println("Combinations TD(41_050_000): " + combinationsTopDown(41_050_000, new int[] { 25,10,5,1 }));
  }

  static long combinationsTopDown(int num, int[] denom) {
    long[][] map = new long[num+1][denom.length];
    // warm-up to avoid stack overflow
    if (num > 10_000) {
      for (int i = 10_000; i < num; i += 10_000) {
        System.out.println("Warm-up: " + i);
        combinationsTopDownCalculation(i, denom, 0, map);
      }
    }
    return combinationsTopDownCalculation(num, denom, 0, map);
  }

  private static long combinationsTopDownCalculation(int num, int[] denom, int idx, long[][] map) {
    if (num < 0) return 0;
    if (num == 0) return 1;

    if (map[num][idx] > 0) return map[num][idx];

    int coin = denom[idx];
    if (idx == denom.length - 1) return num % coin == 0 ? 1 : 0;

    map[num][idx] = combinationsTopDownCalculation(num - coin, denom, idx, map) +
                    combinationsTopDownCalculation(num, denom, idx + 1, map); 

    if (map[num][idx] < 0) {
      System.out.println("num : " + num + " idx: " + idx + " map: " + map[num][idx]); 
      throw new IllegalArgumentException();
    }
    return map[num][idx];
  }

  static int combinationsBottomUp(int num, List<Integer> elements) {
    Map<List<Integer>, Integer> intermediate = Map.of(List.of(), num);
    int count = 0;
    while (!intermediate.isEmpty()) {
      intermediate = intermediate.entrySet().stream()
                                 .flatMap(entry -> elements.stream()
                                                           .map(element ->
                                                              Map.entry(newList(entry.getKey(), element),
                                                                        entry.getValue() - element)))
                                 .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1));
      Iterator<Entry<List<Integer>,Integer>> i = intermediate.entrySet().iterator();
      while (i.hasNext()) {
        Entry<List<Integer>, Integer> next = i.next();
        int current = next.getValue();
        if (current == 0) {
          count++;
        }
        if (current <= 0) i.remove();
      }
    }
    return count;
  }

  private static List<Integer> newList(List<Integer> list, Integer value) {
    return Stream.concat(
      list.stream(),
      Stream.of(value)
    )
    .sorted()
    .collect(toList());
  }
}

