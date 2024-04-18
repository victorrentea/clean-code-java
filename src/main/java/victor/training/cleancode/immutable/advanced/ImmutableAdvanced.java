package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> numbers = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable blue = new Immutable(new Point(1, 2), numbers, new Other(15));
    System.out.println("Before: " + blue);

    // Confused Variable code smell
    // this practice, of reassigning the variable can lead to temporal coupling and confusion in the reader
    Immutable moved = blue.withPoint(wilderness(blue)); // wither returns a changed clone

    System.out.println("Moved object:  " + moved);
  }

  @SneakyThrows
  private static Point wilderness(Immutable immutable) {
    // imagine 1500 lines of code working with immutable object

    // dark, deep logic not expected to change the immutable object x,y

    // quickfix BUG-123124  Fri evening hack
//    immutable.getNumbers().clear();

    // CR: in this wilderness, we need to 'relocate the position of the object on the screen'  like this:
//    immutable.setX(immutable.getX()+1);
//    immutable.setY(immutable.getY()+1);

    // if X and Y change together, they probably belong together
//    return new Immutable(immutable.getPoint().moveBy(1,1),
//        immutable.getNumbers(),
//        immutable.getOther());

    //code smell: "toBuilder"
//    immutable.toBuilder().point(immutable.getPoint().moveBy(1, 1)).numbers().build();

    Method neverDoThat = immutable.point().getClass().getDeclaredMethod("foo");
    neverDoThat.setAccessible(true);
    neverDoThat.invoke(immutable.point());

    return immutable.point().moveBy(1, 1);
  }

}

record Point(int x, int y) {
  public Point moveBy(int dx, int dy) {
    foo2();
    return new Point(x + dx, y + dy);
  }

  //  @NeverToRename // at the app startup reflectively scan all classes for such annoted method and cross check the list
  // you collect vs a stored 'reflective-methods.txt' you have in your source code
  // foo
  // bar
  private void foo2() {
    System.out.println("Hello private");
  }
}

/**
 * @param point   private final Integer x;  private final Integer y; */
@Builder(toBuilder = true)
// DEEP immutable now: all its object graph is unchangeable after instantiation
record Immutable(
    Point point,
    ImmutableList<Integer> numbers,
    Other other) {

  public Immutable withPoint(Point movedPoint) {
    return new Immutable(movedPoint, numbers, other);
  }
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
