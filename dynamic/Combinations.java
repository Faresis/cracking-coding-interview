import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Combinations {
  public static void main(String[] args) {
    for (int i = 1; i < 100; i++) {
      System.out.println("Combinations TD(" + i + "): " + combinationsTopDown(i, List.of(1,5,10,25)));
      System.out.println("Combinations BU(" + i + "): " + combinationsBottomUp(i, List.of(1,5,10,25)));
    }
  }

  static int combinationsTopDown(int num, List<Integer> elements) {
    if (num == 0) return 1;
    if (elements.isEmpty()) return 0;
    if (num < 0) return 0;

    return combinationsTopDown(num - elements.get(0), elements) +
           combinationsTopDown(num, elements.subList(1, elements.size()));   
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

