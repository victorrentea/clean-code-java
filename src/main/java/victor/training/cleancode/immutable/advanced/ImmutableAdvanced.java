package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable changed = wilderness(immutable);
//    immutable = wilderness(immutable);
    foo(changed);
  }

  private static void foo(Immutable immutable) {
    System.out.println("After:  " + immutable);
  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
//    immutable.getList().clear();

    // add +1 to x and y
    return immutable.moveBy(1, 1);
  }

}

// generates equals, hashCode, toString, getters, makes al fields final
record Immutable(
    Integer x,
    Integer y,
    ImmutableList<Integer> list,
    Other other) {

  public Immutable moveBy(int dx, int dy) {
    return new Immutable(x + dx, y + dy, list, other);
  }
}

record Other(int a) {
}
