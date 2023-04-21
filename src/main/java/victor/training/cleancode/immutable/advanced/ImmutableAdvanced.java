package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(final String[] args) {
      final List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

       final Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println("Before: " + immutable);

      final Immutable updated = wilderness(immutable);

      // CR: this code has to work with the same immutable as the initial one, but with a different X computed inside wilderness
      System.out.println("After:  " + updated);
   }

   private static Immutable wilderness(final Immutable immutable) {
      // dark deep logic
//      immutable.getNumbers().add(1);
      final int newX = -1;
      return immutable.withX(newX);
   }
}

class Immutable { // SHALLOW IMMUTABLE
   private final int x;
   private final ImmutableList<Integer> numbers;
   // #5
   private final Other other;

   Immutable(final int x, final ImmutableList<Integer> numbers, final Other other) {
      this.x = x;
      this.numbers = numbers; // List.copyOf(numbers); // #1 Java 11 - malloc
      this.other = other;
   }

   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }

   //   public List<Integer> getNumbers() {
////      return new ArrayList<>(numbers); // #2 copy on getter: - malloc - misleading
//      return Collections.unmodifiableList(numbers); // #3 immutable decorate: - changing the original list passed to ctor -> mutation
//   }
//   public Stream<Integer> numbers() {
//      return numbers.stream(); // #4 strange? - no .contains()
//   }
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

   public Immutable withX(final int newX) {
      return new Immutable(newX, numbers, other);
   }
}

record Other(int a) {
}
