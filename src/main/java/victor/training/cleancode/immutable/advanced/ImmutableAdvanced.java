package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    var list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);
//    list.clear();

    // avoid re-assigning variable
    var translatedImmutable = wilderness(immutable);

    System.out.println("After:  " + translatedImmutable);
  }

  private static Immutable wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
//    immutable.list().clear();
    // I MUST "change" x,y, to add +1
    return new Immutable(immutable.x() + 1, immutable.y() + 1, immutable.list(), immutable.other());
  }
}

record Immutable(
    Integer x,
    Integer y,
    ImmutableList<Integer> list,
    Other other) {
}

//class Immutable {
//  private final Integer x;
//  private final Integer y;
//  //  private final ImmutableList<Integer> list; // best from guava
//  private final List<Integer> list;
//  private final Other other;
//
//  Immutable(Integer x, Integer y, List<Integer> list, Other other) {
//    this.x = x;
//    this.y = y;
//    this.list = list;// protect against others keeping a ref to the mutable arraylist.
//    this.other = other;
//  }
//
////  public Stream<Integer> getList() {
////  public Iterator<Integer> getList() {
////  public Iterable<Integer> getList() { // okish
//
//  public List<Integer> getList() {

////    return new ArrayList<>(list); // malloc
//    return Collections.unmodifiableList(list); // immutable decorator
//  }
//
//  public Integer getX() {
//    return x;
//  }
//
//  public Integer getY() {
//    return y;
//  }
//
//  public Other getOther() {
//    return other;
//  }
//
//  @Override
//  public String toString() {
//    return "Immutable{x=%d, y=%d, numbers=%s, other=%s}".formatted(x, y, list, other);
//  }
//}

record Other(int a) { // j17+
}

//@Value // = generates final class, getter, ctor,tostring, hahscode...
//class Other {
//  int a;
//}
