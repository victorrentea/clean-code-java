package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
      Other other = new Other(13);

      Immutable immutable = new Immutable(2, numbers, other);
      // LOTS OF BUSINESS LOGIC HERE

//      numbers.clear();
//      immutable.getNumbers().clear();
      System.out.println(immutable);
   }
}
// java 15
//record Immutable(int x, List<Integer> numbers, Other other) {
//   public List<Integer> numbers() {
//      return Collections.unmodifiableList(numbers);
//   }
//}

// Immutables // google

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }


   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }

   public int x() {
      return x;
   }
   public List<Integer> numbers() {
      return Collections.unmodifiableList(numbers);
   }
   public Other other() {
      return other;
   }
}

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }
}
