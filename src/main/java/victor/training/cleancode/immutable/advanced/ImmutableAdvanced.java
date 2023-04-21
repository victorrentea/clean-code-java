package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println("Before: " + immutable);

//      numbers.clear();
      wilderness(immutable);

      System.out.println("After:  " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
      immutable.getNumbers().add(1);
   }
}

class Immutable { // SHALLOW IMMUTABLE
   private final int x;
   private final ImmutableList<Integer> numbers;
   // #5
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
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
}

record Other(int a) {
}
