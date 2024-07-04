package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(new Point(1, 2), list, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable moved = immutable.withPoint(wilderness(immutable.point()));
    System.out.println("After:  " + moved);
  }

  private static Point wilderness(Point point) {
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

//    return immutable.withXY(immutable.x() + 1, immutable.y() + 1);
    return point.moveBy(1, 1); // au fromage
  }
}

record Point(int x, int y) {
  public Point moveBy(int dx, int dy) {
    return new Point(x + dx, y + dy);
  }
}
//@Builder(toBuilder = true) // lombok
record Immutable(
//    Integer x,
//    Integer y,
    Point point,
    ImmutableList<Integer> list,
    Other other) { // deep immutable
  //  public Immutable withXY(int x, int y) {// WITHer
//    return new Immutable(x, y, list, other);
//  }
//  public Immutable moveBy(int dx, int dy) {
//    return new Immutable(x + dx, y + dy, list, other);
//  }
  public Immutable withPoint(Point point) {
    return new Immutable(point, list, other);
  }
}

record Other(int a) {
}
