package victor.training.cleancode.immutable.advanced;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    List<Integer> numbers = Stream.of(1, 2, 3).collect(toList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, numbers, new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
  }
}

//este shallow immutable, nu deep immutable
class Immutable {
  private final Integer x;
  private final Integer y;
  private final List<Integer> numbers;
  private final Other other;

  Immutable(Integer x, Integer y, List<Integer> numbers, Other other) {
    this.x = x;
    this.y = y;
    this.numbers = numbers;
    this.other = other;
  }

  public List<Integer> getNumbers() {
    return Collections.unmodifiableList(numbers);
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
    return "Immutable{x=%d, y=%d, numbers=%s, other=%s}".formatted(x, y, numbers, other);
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
