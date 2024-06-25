package victor.training.cleancode.immutable.advanced;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    List<Integer> list = Stream.of(1, 2, 3).collect(toList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
    immutable.list().clear();
  }
}

record Immutable(
    Integer x,
    Integer y,
    List<Integer> list,
    Other other) { // deep immutable
//  Immutable {
//    list = Collections.unmodifiableList(list); // OK #1
//  }
  public List<Integer> list() {
    return Collections.unmodifiableList(list); // OK #2
//    return new ArrayList<>(list);// NO: -performance -misleading for caller
  }
}

record Other(int a) {
}
