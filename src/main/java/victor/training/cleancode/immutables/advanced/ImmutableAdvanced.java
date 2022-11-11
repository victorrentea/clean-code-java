package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.stream.Stream;

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
      immutable.numbers().clear(); // exception is better than silently ignoring the update.
   }
}

record Immutable(int x,
                 ImmutableList<Integer> numbers,
                 Other other) {
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
