package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println(immutable);

      Immutable changed = getDeep(immutable);

//       BAD prazctice : reassign local variables !
//      immutable = getDeep(immutable);
//      immutable = getDeep2(immutable);
//      immutable = getDee3p(immutable);
//      immutable = getDee5p(immutable);
//      immutable = ge7tDeep(immutable);
      // wilderness

      System.out.println(immutable);
   }

   private static Immutable getDeep(Immutable immutable) {
      return deep(immutable);
   }

   private static Immutable deep(Immutable immutable) {
      // exception are better than silent;
      // but exceptions are still runtime
//      immutable.getNumbers().add(123);
      return immutable.withX(-7).withX(-9);
   }
}

//@Entity
class Immutable {
//   @With // "wither"
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

//   protected   Immutable() {} // just for Hibernate . protected not private because sometimes hibernate subclasses your entities (EM.getReference)

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public ImmutableList<Integer> getNumbers() {
//      return new ArrayList<>(numbers);  // - memory - surprise in users.
//      return Collections.unmodifiableList(numbers);
      return numbers;
   }
   public int getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

   @Override
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }

   public Immutable withX(int x) {
      return this.x == x ? this : new Immutable(x, this.numbers, this.other);
   }
}

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

}
