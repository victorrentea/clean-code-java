package victor.training.pure.basic;

import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Other other = new Other(13);
      Immutable immutable = new Immutable(2, numbers, other);

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE // codru des de logica
      // ~25 de buguri in ultimii 2 ani
      // 1000 LOC. eg. alg de calcul de preturi, determinarea loviturii unui erou in fct de itemii skilluri de pe el
      numbers.add(-5);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);

      // aici sa cresti valoarea lui X

//      Immutable newInstance = immutable.withX(immutable.getX() + 1);

   }
}


// definitie: Immutable = obiect a carui stare NU mai poate fi schimbata dupa instantiere
//@RequiredArgsConstructor
//@ToString
//@Getter
//@Data

@Value
class Immutable {
   @With
   int x;
   List<Integer> numbers;
   Other other;
}

//record Immutable(int x, List<Integer> numbers, Other other) {
//}



class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
