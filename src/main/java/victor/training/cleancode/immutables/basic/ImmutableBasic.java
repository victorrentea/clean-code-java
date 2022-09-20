package victor.training.cleancode.immutables.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      dark1000KbizLogic(immutable);
      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static void dark1000KbizLogic(Immutable immutable) {
      Immutable changedCopy = layersBelow(immutable);
      // later
      System.out.println("Some logic that is fixed is x++ " + changedCopy);
   }

   private static Immutable layersBelow(Immutable immutable) {
      // bug fix
      immutable.getNumbers().add(-1); // no error, nothing. just silence. but your change is discarded.
      Immutable changedCopy = new Immutable(immutable.getX() + 1, immutable.getNumbers(), immutable.getOther());
      return changedCopy;
   }
}

// "immutable" = you can't change after you instantiated it
class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   public Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }

   public int getX() {
      return x;
   }
   public List<Integer> getNumbers() {
      return new ArrayList<>(numbers); // malloc / free  inefficient + lying: the client adding to this list MIGHT HAVE the impressiion that it's changing my list. no exeption.
   }
   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
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
