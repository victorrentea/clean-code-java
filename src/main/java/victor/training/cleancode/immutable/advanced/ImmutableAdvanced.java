package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println("Before: " + immutable);

      // reasons to use immutable objects:
      // - thread safety
      // - avoid temporal coupling: data flow is in plain sight

      Immutable changed = wilderness(immutable);

      System.out.println("After:  " + changed);
   }

   private static Immutable wilderness(Immutable immutable) {
      // dark deep logic
      //      immutable.getNumbers().clear();
      Immutable changedCopy = immutable.withX(10);
      return changedCopy;
   }
}

class Immutable { // this is just SHALLOW immutable, not DEEP immutable
   @With
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers; // =List.copyOf(numbers) Java 10
      this.other = other;
   }
   //   public List<Integer> getNumbers() {
   //      return new ArrayList<>(numbers); // 1 inefficient, 2 misleading: silently ignore any changes
   //   }

   //   public List<Integer> getNumbers() {
   //      return unmodifiableList(numbers); // wrap the original list in an unmodifiable wrapper that throws on any change
   //      // misleading> why do we have .add .remove if we can't use them?
   //   }
   public ImmutableList<Integer> getNumbers() { // no longer using Java Collection framework
      // Hiberante cannot persist these> PersistentBag implmenets List
      return numbers;
   }


   public Stream<Integer> numbers() { // a bit extreme
      return numbers.stream();
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
}

record Other(int a) {

}
