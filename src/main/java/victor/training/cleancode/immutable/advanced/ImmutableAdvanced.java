package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    List<Integer> numbers = Stream.of(1, 2, 3).collect(toList()); // ArrayList

//    Immutable immutable = new Immutable(1, numbers, new Other(15));
    Immutable immutable = new Immutable(1, 2, "Street", "City", "State", ImmutableList.of(1, 2, 3), new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);
//immutable.wi
    System.out.println("After:  " + immutable);
  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic

    return immutable.withXY(2, 3);
    // The code above has 2 problems:
    // - they malloc 2 objects
    // - you could be missing a new type called Point
  }

  private static Immutable flow2(Immutable immutable) {
    return immutable.withXY(7, 6);
  }
}
//public HttpHost(String hostname, int port, String scheme) { ... } apache httpclient v5
//public HttpHost(String scheme, String hostname, int port) { ... }
//Imagine if port was a String and you were migrating ... talk about parameter ordering

record Point(int x, int y) {
}
record Immutable(
    Integer x,
    Integer y,
//    Point point, option B: find more types in the code
    String street,
    String city,
    String state,
    ImmutableList<Integer> numbers,
    Other other) {

  //   public Immutable withX(Integer newX) {
//      return new Immutable(newX, y, street, city, state, numbers, other);
//   }
  // option A) more semantic "changes"
  public Immutable withXY(Integer newX, Integer newY) {
    return new Immutable(newX, newY, street, city, state, numbers, other);
  }
}

//class ImmutableBuilder()

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
