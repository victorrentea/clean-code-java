package victor.training.cleancode.immutable.basic;

import lombok.Value;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));


      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      horror(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }
   private static void horror(Immutable immutable) {

   }
}

@Value // = @Getter + @ToString + @EqualsAndHashCode + @AllArgsConstructor
// + all fields are final private
// "shallow" immutable nu "DEEP"
class Immutable {
   Integer x;
   List<Integer> numbers;
   Other other;
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
