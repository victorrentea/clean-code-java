package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.With;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    var list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    var translated = wilderness(immutable);

    System.out.println("After translated:  " + translated);
  }

  private static Immutable wilderness(Immutable immutable) {
//    immutable.list().clear(); // compiler warning
    // dark, deep logic not expected to change the immutable object x,y
//    return new Immutable(immutable.x() + 1,
//                          immutable.y() +1,
//                            ImmutableList.copyOf(immutable.list()),
//                            immutable.other());
    return immutable.translate(1, 1);
  }
}

// with-mania

record Immutable(
    @With
    Integer x,
    @With
    Integer y,
    ImmutableList<Integer> list,
    Other other) {

  // more semantics not just with.with...
  public Immutable translate(int dx, int dy) {
    return withX(x + dx).withY(y + dy);
  }
}
//@Value // <java 17
//class Immutable {
//  Integer x;
//  Integer y;
//  ImmutableList<Integer> list;
//  Other other;
//}

record Other(int a) {

}
