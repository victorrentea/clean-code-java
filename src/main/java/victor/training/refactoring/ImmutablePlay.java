package victor.training.refactoring;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
   public static void main(String[] args) {
//      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Other other = new Other(13);

      Immutable immutable = new Immutable(2, numbers, other);
//      numbers.clear();

      System.out.println(immutable);

//      immutable.getNumbers().add(-1);

      for (Integer number : immutable.getNumbers()) {
         System.out.println("El: " + number);
      }
      System.out.println(immutable);
   }
}

class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;
   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }

   public int getX() {
      return x;
   }

   // most usual
//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers);
//   }

   // a bit too geek
//   public Iterable<Integer> getNumbers() {
//      return numbers;
//   }

   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }



   public Other getOther() {
      return other;
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
