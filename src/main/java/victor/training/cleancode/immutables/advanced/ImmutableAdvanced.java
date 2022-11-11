package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println(immutable);

      // wilderness
      wildCode(immutable);

      System.out.println(immutable);
   }

   private static void wildCode(Immutable immutable) {
      immutable.getNumbers().clear(); // exception is better than silently ignoring the update.
   }
}

class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other; //shallow vs deep immutabiliy

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers =numbers;
      this.other = other;
   }
//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // +1 malloc for the entire array KB/MB
//   }

//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers); //20 bytes the most efficient
//   }

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
