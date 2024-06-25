package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Builder;

import java.util.stream.Stream;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    // new variable
    Immutable movedImmutable = wilderness(immutable);

    System.out.println("After:  " + movedImmutable);
  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
//    immutable.list().clear(); //
//    return new Immutable( #1
//        immutable.x() + 1,
//        immutable.y() + 1,
//        immutable.list(), // coupling to internals
//        immutable.other());
    // worse: #2
//    return immutable.toBuilder()
//        .x(immutable.x() + 1)
//        .y(immutable.y() + 1)
//        .build();

    //better: #3
//    return immutable.withXY(immutable.x()+1, immutable.y()+1);

    // #4
    return immutable.move(1, 1);
  }
}
//@Builder(toBuilder = true) // lombok; bad because it lacks semantics.
record Immutable(
    Integer x,
    Integer y,
    ImmutableList<Integer> list,
    Other other) {
  public Immutable withXY(int newX, int newY) { // wither
    return new Immutable(newX, newY, list, other);
  } // deep immutable

  public Immutable move(int dx, int dy) { // tells a story
    return  new Immutable(x + dx, y + dy, list, other);
  }
//  Immutable {
//    list = Collections.unmodifiableList(list); // OK #1
//  }
//  public List<Integer> list() {
//    return Collections.unmodifiableList(list); // OK #2
//    return new ArrayList<>(list);// NO: -performance -misleading for caller
//  }
}

record Other(int a) {
}
