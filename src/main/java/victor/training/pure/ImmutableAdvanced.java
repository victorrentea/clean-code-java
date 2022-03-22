package victor.training.pure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));

      System.out.println(immutable); // tras de par
      numbers.clear();

      // wilderness mii de linii de cod
      haosPrapad(immutable);

      System.out.println(immutable);

   }

   private static void haosPrapad(Immutable immutable) {

      // un fix tarziu
//      immutable.getNumbers().clear();
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers);
      this.other = other;
   }
   public List<Integer> getNumbers() {
      return unmodifiableList(numbers); // buna solutia,
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

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
