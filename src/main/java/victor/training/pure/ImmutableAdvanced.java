package victor.training.pure;

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

import static java.util.Collections.*;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3);

      Immutable immutable = new Immutable(1, numbers, new Other(15));

      System.out.println(immutable);
//      numbers.clear();
//      immutable.getNumbers().clear();
      Immutable immutable2 = immutable.withX(2);
      System.out.println(immutable);

   }
}

//@Data
//class A {
//   private String a;
//   private String b;
//
//   static {
//      new A().setA("a").setB("b");
//   }
//}

@Value
class Immutable {
   @With
   int x;
   ImmutableList<Integer> numbers;
   @NonNull
   Other other;

//   public Immutable withX(int newX) {
//      return new Immutable(newX, numbers, other);
//   }
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
