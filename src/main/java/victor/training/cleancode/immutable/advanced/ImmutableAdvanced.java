package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println("Before: " + immutable);

      wilderness(immutable);

      System.out.println("After:  " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
      immutable.getNumbers().add(-1);

      System.out.println("elem meu in " + immutable.getNumbers());
   }
}
final class Immutable { // DEEP > shallow immutable
   private final int x;
   private final ImmutableList<Integer> numbers; // #3 treci de la Java collections la guava collections
   // ! Hibernate nu poate persista asa ceva.
   private final Other other;
   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;

//      this.numbers = Collections.unmodifiableList(numbers);
      // #2 intoarce o lista care nu poate fi modificata:
      this.other = other;
   }

   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }

   //   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);  // rau din 1) malloc 2) misleading
//   }
   public int getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

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
