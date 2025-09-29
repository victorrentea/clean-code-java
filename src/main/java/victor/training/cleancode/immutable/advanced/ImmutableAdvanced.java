package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.With;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);
//    list.clear(); // too evil to be true; but possible
    var movedImmutable = wilderness(immutable);

//    for (int i = 0; i < 10_000_000; i++) {
//      immutable = wilderness(immutable); // 600 MB allocated
//    }

    System.out.println("After:  " + movedImmutable);
  }

  private static Immutable wilderness(Immutable immutable) {
//    immutable.getList().clear(); // runtime surprise!
//    immutable.getList().clear(); // runtime surprise!
    // CR: add +1 to x and y
//    return new Immutable(
//        immutable.x() + 1,
//        immutable.y() + 1,
//        immutable.list(),
//        immutable.other()
//    );

//    return immutable
//        .withX(immutable.x() + 1)
//        .withY(immutable.y() + 1);
    return immutable
//        .move(immutable.x() + 1, immutable.y() + 1);
        .moveBy(1, 1);
  }
}

// shallow immutable
record Immutable(
    @With
    Integer x,
    @With
    Integer y,
    ImmutableList<Integer> list,
    Other other) {
//  public Immutable withX(Integer newX) {
//    return new Immutable(newX, this.y, this.list, this.other);
//  }

  public Immutable moveBy(int dx, int dy) {
    return new Immutable(this.x + dx, this.y + dy, this.list, this.other);
  }
}

record Other(int a) {
}