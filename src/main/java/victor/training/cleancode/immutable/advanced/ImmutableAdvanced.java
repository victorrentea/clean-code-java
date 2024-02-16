package victor.training.cleancode.immutable.advanced;

import lombok.With;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    List<Integer> numbers = Stream.of(1, 2, 3).collect(toList()); // ArrayList

    Immutable immutable = new Immutable(new Point(1, 1, Optional.empty()), numbers, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable changed = wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic
    return immutable.withPoint(new Point(2, 2, Optional.empty()));
  }
  private static Immutable colasa(Immutable immutable) {
    // dark, deep logic
    return immutable.withPoint(new Point(2, 2, Optional.empty()));
  }
  private static Immutable colea(Immutable immutable) {
    // dark, deep logic
    return immutable.muta(1,1);
  }
}

record Point(int x, int y, Optional<Integer> z) {}

//@With
record Immutable(@With Point point,
                 List<Integer> numbers,
                 Other other) {
  public Immutable muta(int dx, int dy) {
    return withPoint(new Point(point.x() + dx, point.y() + dy, point.z()));
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
