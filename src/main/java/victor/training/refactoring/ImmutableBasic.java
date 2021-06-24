package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Other other = new Other(13);
      Immutable immutable = new Immutable(2, numbers, other);
      // de aici in jos nu mai pot sa-l schimb pe veci...
      System.out.println(immutable);

      numbers.clear();
      // LOTS OF BUSINESS LOGIC HERE
      for (Integer number : immutable.getNumbers()) {
         System.out.println(number);
      }

      Immutable newImmutable = immutable.withNewX(3); // "wither"s

      System.out.println(immutable);
   }

}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   public Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers); // ImmutableList evita clonari inutile.
      this.other = other;
   }

   public Immutable withNewX(int newX) {
      return new Immutable(newX, getNumbers(), getOther());
   }

   public int getX() {
      return x;
   }
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers);
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
