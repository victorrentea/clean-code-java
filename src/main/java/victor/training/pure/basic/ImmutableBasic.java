package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import io.vavr.control.Try;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
//      Try<Immutable> newChangedObj = innocent(immutable);
      Immutable newChangedObj = innocent(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);

      f(newChangedObj);
   }

   private static void f(Immutable newChangedObj) {
      if (newChangedObj.getX() == 2) {
         System.out.println("Missile");
      }
   }

   private static Immutable innocent(Immutable immutable) {
//      if (true) return Try.failure(new RuntimeException("e"));
//      immutable.getNumbers().clear(); // thte caller might not know about the runtie error
      immutable = immutable.withX(-1);
      immutable = immutable.withX(-1);
      immutable = immutable.withOther(new Other(7));
      immutable = immutable.withOther(new Other(7));
      immutable = immutable.withX(-1);
      immutable = immutable.withOther(new Other(7));
      immutable = immutable.withOther(new Other(7));
      return immutable.withX(-1);
   }

}

//@Value  // = DATA + private final all fields
@Value
class Immutable {
   @With
   int x;
   ImmutableList<Integer> numbers;
   @With
   Other other;
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
