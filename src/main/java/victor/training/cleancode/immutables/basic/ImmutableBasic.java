package victor.training.cleancode.immutables.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));



      System.out.println(immutable);

      adanc(immutable);
      numbers.clear();;
      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static void adanc(Immutable immutable) {
      horror(immutable);
      if (immutable.getNumbers().size() == 5) {
         System.out.println("ceva cod important");
      }
   }
   private static void horror(Immutable immutable) {
      simaiAdanc(immutable);
   }
   private static void simaiAdanc(Immutable immutable) {
      // chestt
      // e tarziu Vinerim =de Neversea, si ... am un bug. ce ma fac? Orice pot
      immutable.getNumbers().add(-1); // nici nu banuiesti ca te arzi
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   public Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers);
      this.other = other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }

   public int getX() {
      return x;
   }

   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // malloc
      return Collections.unmodifiableList(numbers);
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
