package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);
//    list.clear(); // too evil to be true; but possible
    wilderness(immutable);
    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
//    immutable.getList().clear(); // runtime surprise!
//    immutable.getList().clear(); // runtime surprise!
  }
}

// shallow immutable
class Immutable {
  private final Integer x;
  private final Integer y;
  private final ImmutableList<Integer> list;
  private final Other other;

  Immutable(Integer x, Integer y, ImmutableList<Integer> list, Other other) {
    this.x = x;
    this.y = y;
    this.list = list; // 1 malloc
    this.other = other;
  }

  public ImmutableList<Integer> getList() {
//    return List.copyOf(list); // malloc - inefficient,waste
//    return Collections.unmodifiableList(list); // THE WAY
    return list; // THE WAY
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  public Other getOther() {
    return other;
  }

  @Override
  public String toString() {
    return "Immutable{x=%d, y=%d, numbers=%s, other=%s}".formatted(x, y, list, other);
  }
}

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
