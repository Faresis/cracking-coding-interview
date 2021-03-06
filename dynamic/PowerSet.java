import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;

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

    System.out.println("Top - Down. Revised.");
    Set<Set<Character>> set3 = createPowerSet3(input);
    System.out.println(set3);
    System.out.println("Records number: " + set3.size());

    System.out.println("Bottom - Up. Revised.");
    Set<Set<Character>> set4 = createPowerSet4(input);
    System.out.println(set4);
    System.out.println("Records number: " + set4.size());

    System.out.println("Binary ticker.");
    Set<Set<Character>> set5 = createPowerSet5(input);
    System.out.println(set5);
    System.out.println("Records number: " + set5.size());
  }

  // binary ticker
  static Set<Set<Character>> createPowerSet5(Collection<Character> collection) {
    Set<Set<Character>> powerSet = new HashSet<>(Arrays.asList(new HashSet<>()));
    Character[] arr = collection.toArray(new Character[0]);
    for (int i = 0; i < Math.pow(2, arr.length); i++) {
      Set<Character> set = new HashSet<>();
      for (int c = 0; c < arr.length; c++) {
        if ((i & (1 << c)) > 0) {
          set.add(arr[c]);
        }
      }
      powerSet.add(set);
    }
    return powerSet;
  }

  // Bottom - Up. Revised
  static Set<Set<Character>> createPowerSet4(Set<Character> set) {
    Set<Set<Character>> powerSet = new HashSet<>(Arrays.asList(new HashSet<>()));
    for (Character current : set) {
      Set<Set<Character>> toAdd = new HashSet<>();
      for (Set<Character> subSet : powerSet) {
        Set<Character> extendedSubset = new HashSet<>(subSet);
        extendedSubset.add(current);
        toAdd.add(extendedSubset);
      }
      powerSet.addAll(toAdd);
    }
    return powerSet;
  }

  // Top - Down. Revised
  static Set<Set<Character>> createPowerSet3(Set<Character> set) {
    return createPowerSet3Rec(new HashSet<>(set));
  }
  static Set<Set<Character>> createPowerSet3Rec(Set<Character> set) {
    if (set.isEmpty()) return new HashSet<>(Arrays.asList(new HashSet<>()));

    Iterator<Character> it = set.iterator();
    Character current = it.next();
    it.remove();
    Set<Set<Character>> powerSet = createPowerSet3Rec(set);
    Set<Set<Character>> toAdd = new HashSet<>();
    for (Set<Character> subSet : powerSet) {
      Set<Character> extendedSubset = new HashSet<>(subSet);
      extendedSubset.add(current);
      toAdd.add(extendedSubset);
    }
    powerSet.addAll(toAdd);
    return powerSet;
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

