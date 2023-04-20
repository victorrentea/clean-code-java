package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15, "a"));
      System.out.println("Before: " + immutable);

      Immutable updated = wilderness(immutable);
      //      numbers.add(4); unmodifiableList is vulnerable to this mutation

      System.out.println("After: " + updated);
      // i want to use immutable but with a different X assigned inside the wilderness method
   }

   private static Immutable wilderness(Immutable immutable) {
      // dark deep logic
      //      immutable.getNumbers().add(4);
      Integer x = 7;
      Immutable updated = immutable.withX(x);
      return updated;
   }
}

// an immutable object cannot change state after being created
// is SHALLOW immutable now, not DEEP immutable
class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers; // List.copyOf(numbers); // java 10, malloc 😞
      this.other = other;
   }

   //   public List<Integer> getNumbers() {
   //      return new ArrayList<>(numbers);
   //      // 1) malloc
   //      // 2) misleading to clients (an exception would be better)
   //   }

   //   public List<Integer> getNumbers() {
   //      return Collections.unmodifiableList(numbers); // a decorator over the original list that blocks any mutation
   //      // without allocating any memory
   //   }


   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }

   public int getX() {
      return x;
   }

   public Other getOther() {
      return other;
   }

   @Override
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }

   public Immutable withX(Integer x) {
      //      x=8;
      return this.x == x ? this : new Immutable(x, this.numbers, this.other);
   }
}

record Other(int a, String s) { // usable as DTOs(JSON) o objects stored in Mongo/cassandra/nosql
   //   NEVER HIBERNATE!! because of the lifecycle of @Entity
   public Other withA(int newA) {
      return new Other(newA, s);
   }
}
