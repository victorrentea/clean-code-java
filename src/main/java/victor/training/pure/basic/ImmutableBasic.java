package victor.training.pure.basic;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      numbers.clear(); // rar in practica

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers);// waste de memorie.
      this.other = other;
   }
   public int getX() {
      return x;
   }
//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // waste de memorie
//   }
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers); // cea mai raspandita solutie
   }
   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

@ToString
class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
