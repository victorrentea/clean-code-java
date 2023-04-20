package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      wilderness(immutable);
      // reasons to use immutable objects:
      // -
      // -
      //      numbers.add(4); unmodifiableList is vulnerable to this mutation

      System.out.println("After: " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
      //      immutable.getNumbers().add(4);
   }
}

// an immutable object cannot change state after being created
// is SHALLOW immutable now, not DEEP immutable
class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers; // List.copyOf(numbers); // java 10, malloc 😞
      this.other = other;
   }

   //   public List<Integer> getNumbers() {
   //      return new ArrayList<>(numbers);
   //      // 1) malloc
   //      // 2) misleading to clients (an exception would be better)
   //   }

   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers); // a decorator over the original list that blocks any mutation
      // without allocating any memory
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

record Other(int a) {
}
