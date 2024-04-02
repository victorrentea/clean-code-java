package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    var numbers = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, numbers, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable immutableMutat = wilderness(immutable);

    System.out.println("After:  " + immutableMutat);
  }

  private static Immutable wilderness(Immutable immutable) {
    // TODO vreau sa setez x si y la o valoare mai mare cu +1 fata de ce era
    return new Immutable(immutable.x() + 1, immutable.y() + 1, immutable.numbers(), immutable.other());
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
