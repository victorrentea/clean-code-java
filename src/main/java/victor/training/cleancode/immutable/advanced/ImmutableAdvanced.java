package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    var numbers = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, numbers, new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
//    immutable.getNumbers().add(4); // throws UnsupportedOperationException

    // dark, deep logic not expected to change the immutable object x,y
  }
}

//este shallow immutable, nu deep immutable
//@Value // = final fields + getters + equals + hashCode + toString = @Data cu campurile private finale
record Immutable(Integer x,
                 Integer y,
                 ImmutableList<Integer> numbers,
                 Other other) {
}

record Other(int a) {
}
