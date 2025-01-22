package victor.training.cleancode.java.immutable.basic;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable();

      immutable.x = 2;
      immutable.list = list;
      immutable.other = new Other(13);

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.list);
      System.out.println(immutable);
   }
}

class Immutable {
   public Integer x;
   public List<Integer> list;
   public Other other;

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, list, other);
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
