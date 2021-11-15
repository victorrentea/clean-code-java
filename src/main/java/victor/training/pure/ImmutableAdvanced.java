package victor.training.pure;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


class TwoOthers {
   private final Other other;
   private final Other other2;

   TwoOthers(Other other, Other other2) {
      this.other = other;
      this.other2 = other2;
   }

   public Other getOther() {
      return other;
   }

   public Other getOther2() {
      return other2;
   }
}
public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers) // #1 >> GC

          // A no desgign
          .withOther(new Other(1)) // #2 >> GC
          .withOther2(new Other(2))  // #3

          // B mode deep modeling of our world
//          .withTwoOther(new Other(1), new Other(2))  // // better because the design screams that they need to be set togher

          // C most design: allowing me to NOW add logic on class TwoOthers !
//          .withTwoOthers(new TwoOthers(new Other(1), new Other(2)))
          ;
      System.out.println(immutable);

      // wilderness

      System.out.println(immutable);
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other; // optional
   private final Other other2; // optional

//   private final TwoOthers twoOthers;

   Immutable(int x, List<Integer> numbers) { // only for required fields. if THIS grows over 5, I would look to break the class.
      this(x, numbers, null, null);
   }
   public Immutable withOther(Other newOther) {
      return new Immutable(x, numbers, newOther, other2);
   }
   public Immutable withOther2(Other newOther2) {
      return new Immutable(x, numbers, other, newOther2);
   }
   Immutable(int x, List<Integer> numbers, Other other, Other other2) { // canonical
      this.x = x;
      this.numbers = numbers;
      this.other = other;
      this.other2 = other2;
   }
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers);
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
