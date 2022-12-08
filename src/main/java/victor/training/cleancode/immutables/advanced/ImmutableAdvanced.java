package victor.training.cleancode.immutables.advanced;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = null;//new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      wilderness(immutable);
      // reasons to use immutable objects:
      // -
      // -

      System.out.println("After: " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public List<Integer> getNumbers() {
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
