package victor.training.pure;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers =ImmutableList.of(1,2,3);

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println(immutable);

      numbers.add(1);
      // wilderness
      System.out.println(immutable);
   }
}

@Value  // love
class Immutable {
   int x;
   ImmutableList<Integer> numbers;
   Other other;
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
