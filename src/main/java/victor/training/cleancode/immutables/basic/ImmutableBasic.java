package victor.training.cleancode.immutables.basic;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other(13));

      System.out.println(immutable);

      Immutable changedCopy = darkLong10KLOCBizLogic(immutable);
      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.numbers());
      System.out.println(immutable);
   }

   private static Immutable darkLong10KLOCBizLogic(Immutable immutable) {
      another(immutable);
      another(immutable);

      immutable.numbers().clear();

      Immutable immutable2 = immutable.x(immutable.x() + 1);
      another(immutable2);
      another(immutable2);
      return immutable2;
   }

   private static void another(Immutable immutable) {
   }
}

record Immutable(int x, ImmutableList<Integer> numbers, Other other) {
   public Immutable x(int x) {
      return new Immutable(x, numbers, other);
   }
}


//
//@Value
//class Immutable {
//   @With
//   int x;
//   ImmutableList<Integer> numbers;
//   Other other;
//}



//final class Immutable {
//   private final int x;
//   private final ImmutableList<Integer> numbers; // #4 guava, hibernate = BUM
//   private final Other other;
//
//   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//      this.x = x;
////      this.numbers = List.copyOf(numbers); // i still malloc memory but only ONCE per instance. #2 cool, no on @Entity
//      this.numbers = numbers;
//      this.other = other;
//   }
//
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
//   }
//
//   public int getX() {
//      return x;
//   }
//
//   public ImmutableList<Integer> getNumbers() {
////      return new ArrayList<>(numbers); // the most commont, yet most inefficient solution. #1
////      return Collections.unmodifiableList(numbers); // #3 return a decorator (pattern) around the original list.
//      return numbers;
//   }
//
//   public Other getOther() {
//      return other;
//   }
//
//   public Immutable withX(int newX) {
//      return new Immutable(newX, this.numbers, this.other);
//   }
//}

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
