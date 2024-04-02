package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Builder;

import java.util.List;
import java.util.stream.Stream;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    var numbers = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList()); // ArrayList

//    Immutable immutable = //kt: Immutable(x=1, y=2, number=numbers, new Other(15));
    Immutable immutable = //new Immutable(1, 2, numbers, new Other(15));
        Immutable.builder()
            .x(1)
            .y(2)
            .numbers(numbers)
            .other(new Other(15))
            .build();
    System.out.println("Before: " + immutable);

    Immutable immutableMutat = wilderness(immutable);

    System.out.println("After:  " + immutableMutat);
  }

  private static Immutable wilderness(Immutable immutable) {
//    Immutable.builder().
    // TODO vreau sa setez x si y la o valoare mai mare cu +1 fata de ce era
//    return new Immutable(immutable.x() + 1, immutable.y() + 1, immutable.numbers(), immutable.other());
//      return immutable.toBuilder().x(immutable.x() + 1).y(immutable.y() + 1).build();
      // kotlin: return immutable.copy(x = immutable.x + 1, y = immutable.y + 1);

    return immutable.move(1,1);

    // visez: immutable.withPosition(immutable.position().move(1,1));
  }
}
record Point(int x, int y) {
  public Point move(int dx, int dy) {
    return new Point(x + dx, y + dy);
  }
}

//este shallow immutable, nu deep immutable
//@Value // = final fields + getters + equals + hashCode + toString = @Data cu campurile private finale
// nu pui pe @Data niciodata!!! vezi lombok.config
@Builder
      (toBuilder = true) // Doamne fereste!!!
record Immutable(Integer x,
                 Integer y,
                 ImmutableList<Integer> numbers,
                 Other other) {

  // multe d'astea!!!
  public List<Integer> oddNumbers() {
    return numbers.stream().filter(n -> n % 2 == 1).toList();
  }

  public Immutable move(int dx, int dy) { // semantic-rich wither
    return new Immutable(x + dx, y + dy, numbers, other);
  }
}

record Other(int a) {
}
