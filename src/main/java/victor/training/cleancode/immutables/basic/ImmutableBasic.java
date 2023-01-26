package victor.training.cleancode.immutables.basic;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
  public static void main(String[] args) {
    List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

    Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other(13));

    System.out.println(immutable);

    Immutable changed = bizLogic(immutable);
    // LOTS OF BUSINESS LOGIC HERE

    System.out.println(changed.getNumbers());
    System.out.println(changed.getOther());
    System.out.println(changed);
    System.out.println("Original: " + immutable);
  }

  private static Immutable bizLogic(Immutable immutable) {
    Immutable changedCopy = immutable.withX(17);
    deeper(immutable);
    return changedCopy;
  }

  private static void deeper(Immutable immutable) {
    evenDeeper(immutable);
  }

  private static void evenDeeper(Immutable immutable) {
    // intr-un colt, intr-o noapte trista, facand un bugfix un coleg face un "dirty hack"
    //          immutable.getNumbers().add(-1); // bugului lui trece daca face asta. SI fiind Fri noaptea, de ce nu?
    // TODO n-ar trebui sa fac asta, dar repar Luni
  }

}
//// java 17 ROCKS! 🤘
//record ImmutableRecord(int x, ImmutableList<Integer> numbers, Other other)
//{}


// NU poti persista @Value sau ImmutableList cu Hibernate

// immutable = nu poti schima starea obiectului dupa creere
//@Data // e naspa in Lombok
@Value // e cool 😎 face obiecte imutabile (toate campurile-s finale)
        // next level: ImmutableList (guava) in loc de List
class Immutable {
  @With
  int x;
  ImmutableList<Integer> numbers;
  Other other;
}
//final class Immutable {
//   private final int x;
//   private final List<Integer> numbers;
//   private final Other other;
//
//   public Immutable(int x, List<Integer> numbers, Other other) {
//      this.x = x;
//      this.numbers = numbers;
//      this.other = other;
//   }
//
//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers); // creeaza un 'wrapper' peste lista ta originala care blocheaza (throw)
//      // orice apel ce ar modifica lista
//   }
//
//   public Immutable withX(int x) {
//      return new Immutable(x, getNumbers(), getOther());
//   }
//
//   public int getX() {
//      return x;
//   }
//
//   public Other getOther() {
//      return other;
//   }
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
//   }
//}

class Other {
  private final int a;

  public Other(int a) {
    this.a = a;
  }

  public int getA() {
    return a;
  }
}
