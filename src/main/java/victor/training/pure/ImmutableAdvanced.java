package victor.training.pure;

import com.google.common.collect.ImmutableList;
import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));

      System.out.println(immutable); // tras de par
//      numbers.clear();

      // wilderness mii de linii de cod
      immutable = haosPrapad(immutable);

      System.out.println(immutable);

   }

   private static Immutable haosPrapad(Immutable immutable) {
      return immutable.withX(3)
                     .withOther(new Other(7));
//      return immutable.withXOther(new XOther(3, new Other(7)));
   }

   // In designul obiectelor imutabile, esti mult mai agresiv cu a sparge in obiecte mici.

}

// extends e rau. Ai voie doar daca multe clase extind acelasi base.

final class Immutable {
//   private final XOther xother; // grupate impreuna intr-o singura clasa pentru ca se schimba des impreuna
   private final int x;
   @With
   private final ImmutableList<Integer> numbers;
   @With
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }

   Immutable withX(int newX) {
      return new Immutable(newX, getNumbers(), getOther());
   }

   public ImmutableList<Integer> getNumbers() {
      return numbers; // cea mai buna solutie
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
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
