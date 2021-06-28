package victor.training.refactoring;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
//      ImmutableList<Integer> numbers = ImmutableList.of(1,2,3,4,5);
      List<Integer> numbers = Stream.of(1,2,3,4,5).collect(toList());
      Other other = new Other(13);

      Immutable immutable = new Immutable(2, numbers, other);

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable);
//      numbers.clear();

      Immutable immutable2 = immutable.withX(3);
//      immutable.getNumbers().clear();

//      immutable.getNumbers().add(1);
//      for (Integer number : immutable.getNumbers()) {
//         System.out.println(number);
//      }

      System.out.println(immutable2);

      System.out.println(immutable);
      System.out.println(immutable.getX());
   }
}
@Value
class Immutable {
   @With
   int x;
   List<Integer> numbers;
   @NonNull
   Other other;

   public List<Integer> getNumbers() {
      return unmodifiableList(numbers);
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

//   public void setA(int a) {
//      this.a = a;
//   }
}
