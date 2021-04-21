package victor.training.pure;

import com.google.common.collect.ImmutableList;

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
      this.other = requireNonNull(other);
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
