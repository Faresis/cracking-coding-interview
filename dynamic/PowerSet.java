import java.util.Set;
import java.util.HashSet;

public class PowerSet {
  public static void main(String[] args) {
    Set<Character> input = Set.of('a', 'b', 'c', 'd');
    System.out.println("Top - Down");
    Set<Set<Character>> set1 = createPowerSet(input);
    System.out.println(set1);
    System.out.println("Records number: " + set1.size());
    System.out.println("Proposals: " + proposals1);

    System.out.println("Bottom - Up");
    Set<Set<Character>> set2 = createPowerSet2(input);
    System.out.println(set2);
    System.out.println("Records number: " + set2.size());
    System.out.println("Proposals: " + proposals2);
  }

  static long proposals2 = 0;
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
          proposals2++;
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

  static long proposals1 = 0;
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
      proposals1++;
      fillPowerSet(subSet, powerSet);
    }
  }
}

