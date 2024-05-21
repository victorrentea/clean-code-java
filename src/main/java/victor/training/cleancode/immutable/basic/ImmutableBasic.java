package victor.training.cleancode.immutable.basic;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toImmutableList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));


      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      horror(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }
   private static void horror(Immutable immutable) {
      immutable.getNumbers().clear(); // o greseala istorica a lui Java (1995) care a pus .add/.clear pe colectii
   }
}
@Value // = @Getter + @ToString + @EqualsAndHashCode + @AllArgsConstructor
// + all fields are final private
// "shallow" immutable nu "DEEP"
class Immutable {
   Integer x;
   ImmutableList<Integer> numbers; // daca nu ai ORM/JPA e da best!
   Other other;

//   public List<Integer> getNumbers() {
//      // pt @Entity; Decorator Pattern™️ care blocheaza scrierile pe lista "imbracata"
//      return Collections.unmodifiableList(numbers);
//   }

   //   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);
//      // - malloc mult
//      // - clientul chiar poate sa creada ca a sters din lista (fraeru)
//   }

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
