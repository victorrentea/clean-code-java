package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Other other = new Other(13);
      Immutable immutable = new Immutable(2, numbers, other);

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE // codru des de logica
      // ~25 de buguri in ultimii 2 ani
      // 1000 LOC. eg. alg de calcul de preturi, determinarea loviturii unui erou in fct de itemii skilluri de pe el
      numbers.add(-5);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);

   }
}


// definitie: Immutable = obiect a carui stare NU mai poate fi schimbata dupa instantiere
class Immutable {
   private int x;
   private List<Integer> numbers;
   private Other other;

   private Immutable() {} // doar pt hibernate
   public Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers);
      this.other = other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
   }

   public int getX() {
      return x;
   }

//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // waste of GC,CPU,mem
//   }
   public List<Integer> getNumbers() {
      return unmodifiableList(numbers);
   }

   public Other getOther() {
      return other;
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
