package victor.training.refactoring;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
//      ImmutableList<Integer> numbers = ImmutableList.of(1,2,3,4,5);
      List<Integer> numbers = Stream.of(1,2,3,4,5).collect(toList());
      Other other = new Other(13);

      Immutable immutable = new Immutable(2, numbers, other);

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable);
//      numbers.clear();

      Immutable immutable2 = immutable.withX(3);
//      immutable.getNumbers().clear();

//      immutable.getNumbers().add(1);
//      for (Integer number : immutable.getNumbers()) {
//         System.out.println(number);
//      }

      System.out.println(immutable2);

      System.out.println(immutable);
      System.out.println(immutable.getX());
   }
}

class Immutable {
   private final int x;
//   private final ImmutableList<Integer> numbers;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
//      this.numbers = unmodifiableList(numbers);
      this.numbers = new ArrayList<>(numbers);
      this.other = other;
   }
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
   }
   public int getX() {
      return x;
   }
   public List<Integer> getNumbers() {
      return numbers;
   }
//   public List<Integer> getNumbers() {
//      return unmodifiableList(numbers);
//   }

//   public void addNumber(int n) {
//      numbers.add(n);
//   }

   public Other getOther() {
      return other;
   }

   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
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

//   public void setA(int a) {
//      this.a = a;
//   }
}
