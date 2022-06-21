package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = null;//new Immutable(1, numbers, new Other(15));
      System.out.println(immutable);

      // wilderness

       // ok for <5 fields
//      Immutable changedInstance = new Immutable(7, immutable.getNumbers(), immutable.getOther());
      Immutable changedInstance = immutable.withX(7).withOther(new Other(7)); // wither
//      Immutable changedInstance = immutable.builder().withX(7).build();

      System.out.println(immutable);
   }
}

class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public ImmutableList<Integer> getNumbers() {
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

   public Immutable withX(int x) {
      return this.x == x ? this : new Immutable(x, this.numbers, this.other);
   }

   public Immutable withOther(Other newOther) {
      return new Immutable(x, numbers, newOther);
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
