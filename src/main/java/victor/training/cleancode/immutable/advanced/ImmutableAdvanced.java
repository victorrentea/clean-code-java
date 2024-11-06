package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
  }
}

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
}

record Other(int a) {
}
