package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    var list = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    immutable.list().clear();
    // dark, deep logic not expected to change the immutable object x,y
  }
}

//  -----
record Immutable(
    Integer x,
    Integer y,
//    List<Integer> list,  mutable collection 1995-style
    ImmutableList<Integer> list, // guava (google.common)
    Other other) {
//  @Override
//  public List<Integer> list() {
//    return list;//
//    return new ArrayList<>(list);// malloc toata lista + misleading
//    return Collections.unmodifiableList(list); // aloci 10b + erroare
//  }
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
