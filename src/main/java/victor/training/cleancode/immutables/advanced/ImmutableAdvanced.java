package victor.training.cleancode.immutables.advanced;

import lombok.With;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);


      Immutable changed = wilderness(immutable);
      // reasons to use immutable objects:
      // -
      // -b

      System.out.println("After: " + changed);
   }

   private static Immutable wilderness(Immutable immutable) {
      // dark deep logic
//      immutable.getNumbers().add(-1);
      return immutable.withX(-99);
   }
}

class Immutable {
   @With
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   // idee dura : ImmutableList (stil de viata)

//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // malloc + GC si MINCINOS CU CLIENTU :(
//   }

   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers); // mai putina memorie si mai brutal + onest
   }


//   public Iterable<Integer> getNumbers() {
//      return numbers;
//   }


//   public List<? extends Integer> getNumbers() {
//      return numbers;
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

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
