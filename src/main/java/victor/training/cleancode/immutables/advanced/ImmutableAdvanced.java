package victor.training.cleancode.immutables.advanced;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);
      //      numbers.add(99); // too little risk
      wilderness(immutable);
      // reasons to use immutable objects:
      // - peace (don't be afraid to who you give your data)
      // - thread-safe

      System.out.println("After: " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
      //      immutable.getNumbers().clear(); // TODO fix on Mon (and other jokes I tell to myself)
   }
}

class Immutable { // DEEP IMMUTABLE (not only shallow)
   private final int x;
   // ImmutableList (guava) < the state of the art (Google) immutable collections in Java
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers); // the best solution
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
}

// for free just like @Value -> getter, ctor, tostring, hash/eq
record Other(int a) { // java 17 kung fu < DTOs,

}
