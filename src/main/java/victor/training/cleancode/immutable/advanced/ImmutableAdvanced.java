package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(final String[] args) {
      final List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      final Immutable immutable =
              new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println("Before: " + immutable);

      final Immutable updated = wilderness(immutable);

      System.out.println("After:  " + updated);
   }

   private static Immutable wilderness(final Immutable immutable) {
      // dark deep logic
//      immutable.getNumbers().add(-1);
      final int noulX = -7;
      System.out.println("elem meu in " + immutable.getNumbers());
      return immutable.withX(noulX);
   }
}
final class Immutable { // DEEP > shallow immutable
//   @With
   private final int x;
   private final ImmutableList<Integer> numbers; // #3 treci de la Java collections la guava collections
   // ! Hibernate nu poate persista asa ceva.
   private final Other other;

   public Immutable withX(final int x) { // = @With
      return new Immutable(x, numbers, other);
   }

   Immutable(final int x, final ImmutableList<Integer> numbers, final Other other) {
      this.x = x;
      this.numbers = numbers;

//      this.numbers = Collections.unmodifiableList(numbers);
      // #2 intoarce o lista care nu poate fi modificata:
      this.other = other;
   }

   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }

   //   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);  // rau din 1) malloc 2) misleading
//   }
   public int getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

// java 17 equivalent: record Other(int a) {}
@Value // = getter constructor eq/hash tostring
class Other {
   int a;
}