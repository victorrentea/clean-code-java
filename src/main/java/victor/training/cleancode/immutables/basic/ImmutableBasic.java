package victor.training.cleancode.immutables.basic;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      Immutable changed = bizLogic(immutable);
      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(changed.getNumbers());
      System.out.println(changed.getOther());
      System.out.println(changed);
   }

   private static Immutable bizLogic(Immutable immutable) {
//      immutable.setX(17);
      Immutable changedCopy = immutable.withX(17);
      return changedCopy;
   }

}
// immutable = nu poti schima starea obiectului dupa creere
final class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   public Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public Immutable withX(int x) {
      return new Immutable(x, getNumbers(), getOther());
   }
   public int getX() {
      return x;
   }
   public List<Integer> getNumbers() {
      return numbers;
   }
   public Other getOther() {
      return other;
   }
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
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
