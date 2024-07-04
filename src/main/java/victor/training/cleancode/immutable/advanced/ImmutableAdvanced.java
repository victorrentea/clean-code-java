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
    immutable.getList().clear();// bad practice, SOC clientului care crede CA CHIAR ti-a modificat lista
  }
}

class Immutable {
  private final Integer x;
  private final Integer y;
  private final List<Integer> list;
  private final Other other;
  Immutable(Integer x, Integer y, List<Integer> list, Other other) {
    this.x = x;
    this.y = y;
    this.list = list;
    this.other = other;
  }
  public List<Integer> getList() {
//    return new ArrayList<>(list); // #1 prost = malloc + clientul crede ca a modificat lista
    return Collections.unmodifiableList(list); // DA: decoreaza lista originala blocand orice mutatie (pune o coaja peste lista originala))
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
