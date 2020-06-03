import java.util.Map;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Optional;

public class HashTable<K, V> { 
  public static void main(String[] args) {
    HashTable<String, String> ht = new HashTable<>(16);

    ht.put("1", "one");
    ht.put("2", "two");
    ht.put("3", "three");
    ht.put("99", "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");

    System.out.println(ht);

    System.out.println("1: " + ht.get("1"));
    System.out.println("has 1: " + ht.contains("1"));
    System.out.println("has 7: " + ht.contains("7"));
    ht.put("1", "other one");
    System.out.println("has 1: " + ht.contains("1"));
    System.out.println("1: " + ht.get("1"));
  }

  class Entry<K, V> {
    K key;
    V value;

    Entry(K key, V value) { this.key = key; this.value = value; }

    @Override
    public String toString() {
      return "{ key: " + key + ", value: " + value + " }";
    }
  }
  final ArrayList<LinkedList<Entry<K, V>>> entries;
  final int size;

  HashTable(int size) {
    this.size = size;
    this.entries = new ArrayList<>(size);
    for(int i = 0; i < size; i++)
      this.entries.add(new LinkedList<>());
  }

  V put(K key, V value) {
    V result = null;
    int idx = key.hashCode() % size;
    LinkedList<Entry<K, V>> node = entries.get(idx);
    Optional<Entry<K, V>> entry = node.stream().filter(e -> e.key.equals(key)).findAny();
    if (entry.isPresent()) {
      result = entry.get().value;
      entry.get().value = value;
    } else {
      node.add(new Entry<K, V>(key, value));
    }
    return result;
  }

  V get(K key) {
    int idx = key.hashCode() % size;
    LinkedList<Entry<K, V>> node = entries.get(idx);
    Optional<Entry<K, V>> entry = node.stream().filter(e -> e.key.equals(key)).findAny();
    return entry.map(e -> e.value).orElse(null);
  }

  boolean contains(K key) {
    int idx = key.hashCode() % size;
    LinkedList<Entry<K, V>> node = entries.get(idx);
    return node.stream().filter(e -> e.key.equals(key)).findAny().isPresent();
  }

  @Override
  public String toString() {
    return this.entries.toString();
  }
}
