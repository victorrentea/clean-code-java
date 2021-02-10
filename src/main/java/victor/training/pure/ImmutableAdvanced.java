package victor.training.pure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));

//      numbers.clear();

//      immutable.getNumbers().clear();
      for (Integer number : immutable.getNumbers()) {
         System.out.println(number);
      }
      immutable = immutable.withX(3);

      System.out.println(immutable);


   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = requireNonNull(numbers);
      this.other = requireNonNull(other);
   }

   @Override
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }

   public int getX() {
      return x;
   }

//   public List<Integer> getNumbers() {
//      return unmodifiableList(numbers);
//   }
   public Iterable<Integer> getNumbers() {
      return numbers;
   }

   public Other getOther() {
      return other;
   }

   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
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

   @Override
   public String toString() {
      return "Other{" +
             "a=" + a +
             '}';
   }
}
