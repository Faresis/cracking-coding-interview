import java.util.LinkedList;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class Shelter {
  static class Animal {
    String name;
    Animal(String name) { this.name = name; }
    @Override public String toString() { return this.name; }
  }
  static class Dog extends Animal {
    Dog(String name) { super(name); }
  }
  static class Cat extends Animal {
    Cat(String name) { super(name); }
  }
  private static class StoredDog {
    Dog dog;
    LocalDateTime arrived = LocalDateTime.now();
    StoredDog(Dog dog) { this.dog = dog; }
  }
  private static class StoredCat {
    Cat cat;
    LocalDateTime arrived = LocalDateTime.now();
    StoredCat(Cat cat) { this.cat = cat; }
  }

  private LinkedList<StoredDog> dogs = new LinkedList<>();
  private LinkedList<StoredCat> cats = new LinkedList<>();
  
  void enqueue(Dog dog) {
    this.dogs.add(new StoredDog(dog));
  }

  void enqueue(Cat cat) {
    this.cats.add(new StoredCat(cat));
  }

  Dog dequeueDog() {
    return this.dogs.remove().dog;
  }

  Cat dequeueCat() {
    return this.cats.remove().cat;
  }

  Animal dequeueAny() {
    if (this.cats.isEmpty() && this.dogs.isEmpty())
      throw new NoSuchElementException();
    if (this.cats.isEmpty()) return this.dequeueDog();
    if (this.dogs.isEmpty()) return this.dequeueCat();
    
    return older(this.dogs.getFirst(), this.cats.getFirst()) ?
           this.dequeueDog() : this.dequeueCat();
  }

  private static boolean older(StoredDog dog, StoredCat cat) {
    return dog.arrived.isBefore(cat.arrived);
  }

  public static void main(String[] args) throws InterruptedException {
    Shelter shelter = new Shelter();
    shelter.enqueue(new Dog("1"));
    Thread.sleep(1000);
    shelter.enqueue(new Dog("2"));
    Thread.sleep(1000);
    shelter.enqueue(new Dog("3"));
    Thread.sleep(1000);
    shelter.enqueue(new Cat("4"));
    Thread.sleep(1000);
    shelter.enqueue(new Dog("5"));
    Thread.sleep(1000);
    shelter.enqueue(new Cat("6"));
    Thread.sleep(1000);
    shelter.enqueue(new Cat("7"));
    Thread.sleep(1000);
    shelter.enqueue(new Cat("8"));
    Thread.sleep(1000);
    shelter.enqueue(new Dog("9"));

    System.out.println(shelter.dequeueAny()); // 1
    System.out.println(shelter.dequeueDog()); // 2
    System.out.println(shelter.dequeueCat()); // 4
    System.out.println(shelter.dequeueAny()); // 3
    System.out.println(shelter.dequeueAny()); // 5
    System.out.println(shelter.dequeueDog()); // 9
    System.out.println(shelter.dequeueAny()); // 6
    System.out.println(shelter.dequeueAny()); // 7
    System.out.println(shelter.dequeueAny()); // 8
  }
}

