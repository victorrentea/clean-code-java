package victor.training.cleancode.immutable.basic;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }
}

// unei clase imutabile nu ii poti schimba starea dupa ce ai instantiat-o
final class Immutable {
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

   public int getX() {
      return x;
   }

   public List<Integer> getNumbers() {
      return numbers;
   }

   public Other getOther() {
      return other;
   }
}

class Other {
   private int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

   public void setA(int a) {
      this.a = a;
   }
}
