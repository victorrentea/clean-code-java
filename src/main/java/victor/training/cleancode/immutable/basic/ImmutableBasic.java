package victor.training.cleancode.immutable.basic;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable();

      immutable.x = 2;
      immutable.numbers = numbers;
      immutable.other = new Other(13);

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.numbers);
      System.out.println(immutable);
   }
}

class Immutable {
   public Integer x;
   public List<Integer> numbers;
   public Other other;

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

class Other {
   private int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

   public void setA(int a) {
      this.a = a;
   }
}
