package victor.training.cleancode.immutable.basic;

import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable mutabil = new Immutable();

      mutabil.x = 2;
      mutabil.numbers = numbers;
      mutabil.other = new Other(13);

      System.out.println(mutabil);

      // LOTS OF BUSINESS LOGIC HERE

      Immutable i = mutabil
          .setX(1)
          .setOther(new Other(15));
      System.out.println(mutabil.numbers);
      System.out.println(mutabil);
   }
}

@Data
class Immutable {
   public Integer x;
   public List<Integer> numbers;
   public Other other;

//   public Immutable setX(Integer x) {
//      this.x = x;
//      return this; // asa genereaza Lombok daca vezi lombok.config
//   }

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
