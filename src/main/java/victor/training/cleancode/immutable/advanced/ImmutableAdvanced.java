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
    immutable.getList().clear();

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
  }
}

class Immutable { // shallow immutable
  private final Integer x;
  private final Integer y;
  private final ImmutableList<Integer> list; // NOT friends with Hibernate, but OK with Jackson, Mongo...
  private final Other other;

  Immutable(Integer x, Integer y, ImmutableList<Integer> list, Other other) {
    this.x = x;
    this.y = y;
    this.list = list;
    this.other = other;
  }

  public ImmutableList<Integer> getList() {
    return list;
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
