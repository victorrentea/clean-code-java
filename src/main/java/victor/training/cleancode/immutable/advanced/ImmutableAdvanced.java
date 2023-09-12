package victor.training.cleancode.immutable.advanced;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      numbers.clear(); // in practica e fff mic riscul asta.
      wilderness(immutable);
      // reasons to use immutable objects:
      // -
      // -

      System.out.println("After:  " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic sute de linii de cod
//      immutable.getNumbers().add(-1); // hackuri POC
//      immutable.getOther().setA()
   }
}
// imutabil = obiect a carui stare nu o poti modifica dupa instantiere.
// "shallow (pe primul nivel) vs deep(in jos pe toate altele ob referite)" clone, immutable, equals
class Immutable {
   private final Integer x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(Integer x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers); // #3 te protejezi si de dusmani care tin pointer la colectia pe care ti-o dau
      this.other = other;
   }
   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // #1 -malloc -misleading ca nu crapa in client
      return Collections.unmodifiableList(numbers); // #2 wrapper peste lista initiala care blocheaza orice modificare "decorator pattern"
      // +nu malloc mult, +CRAPA
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

//record Other(int a) {}

@Value // = ❤️
//@Data = 🤢
class Other {
    int a;
//    List<Integer>
}
