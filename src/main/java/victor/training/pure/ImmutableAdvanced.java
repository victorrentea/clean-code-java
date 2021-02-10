package victor.training.pure;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));

//      numbers.clear();

//      immutable.getNumbers().clear();
      for (Integer number : immutable.getNumbers()) {
         System.out.println(number);
      }
      immutable = immutable.withX(3);

      System.out.println(immutable);


   }
}
@Value
class Immutable {
   @With
   int x;
   @NonNull
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

   @Override
   public String toString() {
      return "Other{" +
             "a=" + a +
             '}';
   }
}
