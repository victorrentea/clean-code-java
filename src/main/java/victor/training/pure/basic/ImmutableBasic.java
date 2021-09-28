package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.ToString;
import lombok.Value;

import javax.print.attribute.standard.MediaSize.Other;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other2(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE

      immutable.numbers().add(1);

      Immutable nou = immutable.withX(3);

      System.out.println(immutable.numbers());
      System.out.println(immutable);
   }
}

// === @LOmbok cu 1 diferenta: getterii nu mai au prefixul "get"
record Immutable(int x, List<Integer> numbers, Other2 other) {
   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
   }
}

@ToString
class Other2 {
   private final int a;

   public Other2(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
