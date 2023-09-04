package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      // reasons to use immutable objects:
      Immutable alta = wilderness(immutable);
      // -
      // -

      // Logica de mai jos trebuie sa ruleze cu un obiect immutable care sa fie exact ca cel de la :14
      // dar cu un x dat de metoda 'wilderness()'
      System.out.println("After:  " + alta);
   }

   private static Immutable wilderness(Immutable immutable) {
      // dark deep logic
//      immutable.getNumbers().add(99);
      int x = 10;
      return immutable.withX(x); // copie;
   }
}

// deep immutable object
class Immutable {
   @With // "witheri"
   private final Integer x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(Integer x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      // memory waste
//      this.numbers = new ArrayList<>(numbers); //
//      this.numbers = List.copyOf(numbers); //
      this.numbers = numbers; //
      this.other = other;
   }
   public ImmutableList<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // cel mai ineficient> orice get aloca memorie!
//      return Collections.unmodifiableList(numbers); // decorator peste lista originala care blocheaza .add .remove .set
      return numbers;
   }
   public Integer getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

record Other(int a) {
}
