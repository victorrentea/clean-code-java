package victor.training.cleancode.immutable.advanced;

import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    List<Integer> numbers = Stream.of(1, 2, 3).collect(toList()); // ArrayList

    Immutable immutable = new Immutable(1, 1, numbers, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable changed = wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic
    return immutable.withXY(2, 2);
  }
  private static Immutable colasa(Immutable immutable) {
    // dark, deep logic
    return immutable.withXY(2, 2);
  }
  private static Immutable colea(Immutable immutable) {
    // dark, deep logic
    return immutable.withXY(2, 2);
  }
}

//@With
record Immutable(Integer x,
                 Integer y,
                 List<Integer> numbers,
                 Other other) {

  public Immutable withXY(int x, int y) {
    return new Immutable(x, y, numbers, other);
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
