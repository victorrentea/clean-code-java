package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.ToString;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE

      immutable.getNumbers().add(1);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }
}

@Value
class Immutable {
   int x;
   ImmutableList<Integer> numbers;
   Other other;
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
