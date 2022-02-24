package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      bagIn1000LiniiDeLogica(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static void bagIn1000LiniiDeLogica(Immutable immutable) {
      // EVIL CODED
//      if (tarziu si bug) {
//         immutable.getNumbers().add(-1);
//         immutable.getOther().set
//      }
   }
}

// e preferabil sa intorci rezultate, nu sa scrii TU ca sa citesc EU. Intoarce-mi, NU SCRIE TU!
// nivel 0 (acum): persist direct
// nivel 1 (mai bun): change-uri pe obiecte din memorie
// nivel 2 (FP): nici macar nu mai modifici obiecte, ci creezi altele noi si le intorci

class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }

   public int getX() {
      return x;
   }

   public ImmutableList<Integer> getNumbers() {
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

}
