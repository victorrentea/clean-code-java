package victor.training.refactoring;

import com.google.common.collect.ImmutableList;

public class ImmutablePlay {
   public static void main(String[] args) {
//      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Other other = new Other(13);

      Immutable immutable = new Immutable(2, numbers, other);
//      numbers.clear();

      System.out.println(immutable);

//      immutable.numbers().add(-1);

      for (Integer number : immutable.numbers()) {
         System.out.println("El: " + number);
      }
      System.out.println(immutable);
   }
}

record Immutable(int x, ImmutableList<Integer> numbers, Other other) {
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
