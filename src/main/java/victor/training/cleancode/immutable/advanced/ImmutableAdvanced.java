package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable moved = wilderness(immutable);
    System.out.println("After:  " + moved);

  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
//    immutable.getList().clear();// deprecation warning in IDE
    // TODO wilderness poate muta x-y al immutable
//    return new Immutable(
//        immutable.x() + 1,
//        immutable.y() + 1,
//        immutable.list(),
//        immutable.other());
    // lui victor nu-i place
//    return immutable.toBuilder()
//        .x(immutable.x() + 1)
//        .y(immutable.y() + 1)
//        .build();

    return immutable.withXY(immutable.x() + 1, immutable.y() + 1);
  }
}

//@Builder(toBuilder = true) // lombok
record Immutable(
    Integer x,
    Integer y,
    ImmutableList<Integer> list,
    Other other) { // deep immutable
  public Immutable withXY(int x, int y) {// WITHer
    return new Immutable(x, y, list, other);
  }
}

record Other(int a) {
}
