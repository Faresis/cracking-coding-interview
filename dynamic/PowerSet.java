import java.util.Set;
import java.util.HashSet;

public class PowerSet {
  public static void main(String[] args) {
    System.out.println(createPowerSet(Set.of('a', 'b', 'c', 'd')));
  }

  static Set<Set<Character>> createPowerSet(Set<Character> set) {
    Set<Set<Character>> powerSet = new HashSet<>();
    fillPowerSet(set, powerSet);
    return powerSet;
  }

  static void fillPowerSet(Set<Character> set, Set<Set<Character>> powerSet) {
    if (powerSet.contains(set)) return;

    powerSet.add(set);
    System.out.println(powerSet);

    for (Character c : set) {
      Set<Character> subSet = new HashSet<>(set);
      subSet.remove(c);
      fillPowerSet(subSet, powerSet);
    }
  }
}

