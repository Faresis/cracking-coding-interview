import java.util.Set;
import java.util.HashSet;

public class PowerSet {
  public static void main(String[] args) {
    System.out.println("Top - Down");
    System.out.println(createPowerSet(Set.of('a', 'b', 'c', 'd')));
    System.out.println("Bottom - Up");
    System.out.println(createPowerSet2(Set.of('a', 'b', 'c', 'd')));
  }

  // Bottom - Up
  static Set<Set<Character>> createPowerSet2(Set<Character> set) {
    Set<Set<Character>> powerSet = new HashSet<>();
    for (Character c : set) {
      powerSet.add(new HashSet<>(c));
    }
    boolean wasAdded = true;
    while (wasAdded) {
      wasAdded = false;
      for (Character c : set) {
        Set<Set<Character>> toBeAdded = new HashSet<>();
        for (Set<Character> subSet : powerSet) {
          if (subSet.contains(c)) continue;
          Set<Character> proposal = new HashSet<>(subSet);
          proposal.add(c);
          if (!powerSet.contains(proposal)) {
            toBeAdded.add(proposal);
            wasAdded = true;
          }
        }
        powerSet.addAll(toBeAdded);
      }
    }
    return powerSet;
  }

  // Top - Down
  static Set<Set<Character>> createPowerSet(Set<Character> set) {
    Set<Set<Character>> powerSet = new HashSet<>();
    fillPowerSet(set, powerSet);
    return powerSet;
  }

  static void fillPowerSet(Set<Character> set, Set<Set<Character>> powerSet) {
    if (powerSet.contains(set)) return;

    powerSet.add(set);

    for (Character c : set) {
      Set<Character> subSet = new HashSet<>(set);
      subSet.remove(c);
      fillPowerSet(subSet, powerSet);
    }
  }
}

