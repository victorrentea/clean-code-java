package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3).collect(toImmutableList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

//      numbers.clear(); // in practica e fff mic riscul asta.

      Immutable updated = wilderness(immutable); // temporal coupled to next line

      System.out.println("After:  " + updated); // folsind var diferite (nu reciclam var) te ajuta compilerul sa te prinda
   }

   private static Immutable wilderness(Immutable immutable) {
      // dark deep logic sute de linii de cod
      // callerul se va prinde doar la runtime de eroare
      // acum pe ImmutableList : deprecated
//      immutable.numbers().add(-1); // hackuri POC
//      immutable.getOther().setA()
      if (immutable.numbers().size() > 2) {
         return immutable.withX(7);
      } else {
         return immutable;
      }
   }
}
// imutabil = obiect a carui stare nu o poti modifica dupa instantiere.
// "shallow (pe primul nivel) vs deep(in jos pe toate altele ob referite)" clone, immutable, equals
// hibernate + immutabile != ❤️
record Immutable(Integer x, ImmutableList<Integer> numbers, Other other) {
   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
   }
   // +din semnatura declari ca vrei un subtip de List care nu poate fi mutat
   // +deprecheaza .add .remove (mutatorii)
   // -nu merge cu Hibernate. Dar DA cu Mongo,Cassandra,Jackson
}


//class Immutable {
//   private final Integer x;
//   private final List<Integer> numbers;
//   private final Other other;
//
//   Immutable(Integer x, ImmutableList<Integer> numbers, Other other) {
//      this.x = x;
//      this.numbers = new ArrayList<>(numbers); // #3 te protejezi si de dusmani care tin pointer la colectia pe care ti-o dau
//      this.other = other;
//   }
//   public List<Integer> getNumbers() {
////      return new ArrayList<>(numbers); // #1 -malloc -misleading ca nu crapa in client
//      return Collections.unmodifiableList(numbers); // #2 wrapper peste lista initiala care blocheaza orice modificare "decorator pattern"
//      // +nu malloc mult, +CRAPA
//   }
//   public Integer getX() {
//      return x;
//   }
//   public Other getOther() {
//      return other;
//   }
//
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
//   }
//}

//record Other(int a) {}

@Value // = ❤️
//@Data = 🤢
class Other {
    int a;
//    List<Integer>
}
