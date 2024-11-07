package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Builder;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    Immutable immutable2 = immutable.withList(wilderness(immutable));

    System.out.println("After:  " + immutable2);
  }

  private static ImmutableList<Integer> wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
    // i need to remove odd numbers from the list
    ImmutableList<Integer> evens = immutable.list().stream()
        .filter(i -> i % 2 == 0)
        .collect(toImmutableList());

    //    return new Immutable(immutable.x(), immutable.y(), evens, immutable.other());
//    return immutable.withList(evens); // better
    return evens;
  }

}

@Builder // a workaround for a language weakness: calling a large constructor w/o named parameters
record Immutable(
    Integer x,
    Integer y,
    ImmutableList<Integer> list,
    Other other) {
  //discouraged:
//  public Immutable(Integer x, Integer y, Other other) {
//    this(x, y, ImmutableList.<Integer>of(), other);
//  }
  // static factory method
  public static Immutable of(Integer x, Integer y, Other other) {
//    if (...)
    return new Immutable(x, y, ImmutableList.of(), other);
  }

  public Immutable withList(ImmutableList<Integer> evens) { // withers. the equivalent of setters
    return new Immutable(x(), y(), evens, other());
  }
}

record Other(int a) {
}
