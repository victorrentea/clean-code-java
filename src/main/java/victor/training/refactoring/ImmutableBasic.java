package victor.training.refactoring;

import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Other other = new Other(13);
      Immutable immutable = new Immutable(2, numbers, other);
      // de aici in jos nu mai pot sa-l schimb pe veci...
      System.out.println(immutable);

      numbers.clear();
      // LOTS OF BUSINESS LOGIC HERE
      for (Integer number : immutable.getNumbers()) {
         System.out.println(number);
      }

      Immutable newImmutable = immutable.withX(3); // "wither"s

      System.out.println(immutable);
   }

}

@Value
class Immutable {
   @With
   int x;
   List<Integer> numbers;
   Other other;
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers);
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
