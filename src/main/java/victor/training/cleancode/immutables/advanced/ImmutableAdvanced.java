package victor.training.cleancode.immutables.advanced;

import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = null;//new Immutable(1, numbers, new Other(15));
      System.out.println(immutable);

      // wilderness
//      List<Immutable> java95;
//      java95.stream().map(it.x)

      System.out.println(immutable);
   }
}
class Immutable {
   private final int x;
   private final List<Integer> numbers;
//   private final Map<Integer, String> numberToString;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public List<Integer> getNumbers() {
      return numbers;
//      return numberToString.keySet().stream().sorted().collect(toList());
   }
   public int getX() {
      return x;
   }

   public List<Integer> getOddNumbers() {
      return numbers.stream().filter(n ->n%2==1).collect(toList());
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
