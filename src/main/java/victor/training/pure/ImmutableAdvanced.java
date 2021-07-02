package victor.training.pure;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import javax.print.attribute.standard.MediaSize;

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
//record Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//
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
