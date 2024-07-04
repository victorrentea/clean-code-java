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
    immutable.getList().clear();// deprecation warning in IDE
  }
}

class Immutable {
  private final Integer x;
  private final Integer y;
  // nu poti folosi ImmutanleList in @Entity de hibernate
  private final ImmutableList<Integer> list; // e de la google si repara java. guava = google java = google-java-commoms
  private final Other other;

  Immutable(Integer x, Integer y, ImmutableList<Integer> list, Other other) {
    this.x = x;
    this.y = y;
    this.list = list;
    this.other = other;
  }

  public ImmutableList<Integer> getList() {
//    return new ArrayList<>(list); // #1 prost = malloc + clientul crede ca a modificat lista
//    return Collections.unmodifiableList(list); // DA: decoreaza lista originala blocand orice mutatie (pune o coaja peste lista originala))
    return list; // DA: decoreaza lista originala blocand orice mutatie (pune o coaja peste lista originala))
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
