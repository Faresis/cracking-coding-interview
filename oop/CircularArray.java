import java.util.ArrayList;
import java.time.Instant;
import java.util.Iterator;

public class CircularArray<E> implements Iterable<E> {
  private final ArrayList<E> list;
  private final int size;
  private int start = 0;
  private Instant lastUpdated = Instant.now();

  CircularArray(int size) {
    this.size = size;
    this.list = new ArrayList<E>(size);
    for (int i = 0; i < size; i++)
      this.list.add(null);
  }

  void rotate(int shift) {
    this.lastUpdated = Instant.now();
    this.start += shift;
    while (this.start < 0) this.start += this.size;
  }

  E get(int idx) {
    return this.list.get(shift(idx));
  }

  E set(int idx, E element) {
    this.lastUpdated = Instant.now();
    return this.list.set(shift(idx), element);
  }

  E remove(int idx) {
    return this.set(idx, null);
  }

  private int shift(int idx) {
    return (this.start + idx) % this.size; 
  }

  @Override
  public Iterator<E> iterator() {
    return new CircularArrayIterator();
  }

  class CircularArrayIterator implements Iterator<E> {
    private Instant createdTime = Instant.now();
    private int idx = -1;
    private boolean nextCalled = false;

    @Override
    public boolean hasNext() {
      if (this.createdTime.isBefore(CircularArray.this.lastUpdated))
        throw new IllegalStateException("The underlying collection was modified.");

      return idx < (CircularArray.this.size - 1);
    }

    @Override
    public E next() {
      if (this.createdTime.isBefore(CircularArray.this.lastUpdated))
        throw new IllegalStateException("The underlying collection was modified.");

      this.nextCalled = true;
      return CircularArray.this.get(++idx);
    }

    @Override
    public void remove() {
      if (this.createdTime.isBefore(CircularArray.this.lastUpdated))
        throw new IllegalStateException("The underlying collection was modified.");
      if (!this.nextCalled)
        throw new IllegalStateException("Next wasn't called.");

      this.nextCalled = false;
      CircularArray.this.remove(idx);
    }
  }

  public static void main(String[] args) {
    int size = 7;
    CircularArray<Integer> a = new CircularArray<Integer>(size);
    for (int i = 0; i < size; i++)
      a.set(i, i);

    System.out.println("Regular for.");
    for (int i = 0; i < size; i++)
      System.out.println(a.get(i));
    System.out.println("For each.");
    for (Integer i : a)
      System.out.println(i);

    System.out.println("Rotated 5.");
    a.rotate(5);
    System.out.println("Regular for.");
    for (int i = 0; i < size; i++)
      System.out.println(a.get(i));
    System.out.println("For each.");
    for (Integer i : a)
      System.out.println(i);

    System.out.println("Rotated -10.");
    a.rotate(-10);
    System.out.println("Regular for.");
    for (int i = 0; i < size; i++)
      System.out.println(a.get(i));
    System.out.println("For each.");
    for (Integer i : a)
      System.out.println(i);
  }
}

