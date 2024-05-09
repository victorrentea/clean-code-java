package victor.training.cleancode.immutable.basic;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

//      immutable.setX(2);
//      immutable.setNumbers(numbers);
//      immutable.setOther(new Other(13));
      Immutable immutable = new Immutable(2, numbers, new Other(13));


      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      wilderness(immutable);

      System.out.println(immutable);
   }

   private static void wilderness(Immutable immutable) {
      immutable.getNumbers().clear();
   }
}

// shallow-immutable: doar campurile mele nu si ale obiectelor referite de mine
// nimanui nu-i pasa de shallow immutable
// noi vrem deep-immutable
class Immutable {
   private final Integer x;
   private final List<Integer> numbers;
   private final Other other;
   public Immutable(Integer x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public Integer getX() {
      return x;
   }
   public List<Integer> getNumbers() {
      return numbers;
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
