package victor.training.pure;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println(immutable);

      // wilderness
//      immutable.getNumbers().clear();
      for (Integer number : immutable.getNumbers()) {
         System.out.println(number);
      }

      System.out.println(immutable);

      Immutable immutable1 = immutable.         withX(5);
   }
}

class Immutable {
   @With
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers);
   }

//   public void addNumber(int newNumber) {
////      numbers.add()
//      return new Immutable(x, aNewListWithTheNewNumber) // ,malloc --> GC
//   }
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

//   public Immutable withX(int newX) {
//      return new Immutable(newX, numbers, other);
//   }
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
