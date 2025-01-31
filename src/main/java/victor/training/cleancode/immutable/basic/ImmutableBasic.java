package victor.training.cleancode.immutable.basic;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, list, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      immutable.getList().clear();

      System.out.println(immutable.getList());
      System.out.println(immutable);
   }
}
// unui obiect imutabil nu ii poti schimba starea dupa instantiere
class Immutable { // = deep immutable: nu poti schimba nici pe el nici obiectele referite
   // poti da o instanta din aceasta clasa oricui, fara sa te temi ca va fi modificata
   private final Integer x;
   private final List<Integer> list;
   private final Other other;

   public Immutable(Integer x, List<Integer> list, Other other) {
      this.x = x;
      this.list = list;
      this.other = other;
   }

   public Integer getX() {
      return x;
   }

   public List<Integer> getList() {
      return Collections.unmodifiableList(list); // readonly collection
   }

   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, list, other);
   }
}

//evita asta pt ca:
// method reference limitation Other::a
// breaks the uniform interface: obiectele (OOP) ar trebui sa expuna metode, nu atribute, pentru a putea adauga ulterior metode aditionale
class Other {
   public final int a;
   public Other(int a) {
      this.a = a;
   }
}
