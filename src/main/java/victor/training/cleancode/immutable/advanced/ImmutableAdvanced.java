package victor.training.cleancode.immutable.advanced;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);
      numbers.add(-1);

      wilderness(immutable);
      // reasons to use immutable objects:
      // -
      // -

      System.out.println("After: " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
      //      immutable.getNumbers().add(-1);
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = List.copyOf(numbers); // opt3
      this.other = other;
   }
   //   public List<Integer> getNumbers() {
   //      return new ArrayList<>(numbers); // +1 malloc , si misleading
   //   }
   //   public List<Integer> getNumbers() { // opt2
   //      return Collections.unmodifiableList(numbers); // un decorator peste lista originala
   //      // care blocheaza (arunca ex) la orice incercare de a modifica
   //      // eficient cu mem
   //   }


   public List<Integer> getNumbers() {
      return numbers;// opt 3
   }

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

// PE  @Entity : @Getter
//@Data // niciodata

// pe non-@Entity: toate asa:
@Value // @Data + toate campurile finale = getter ctor tostring hash/eq
class Other {
   int a;
}
