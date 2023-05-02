package victor.training.cleancode.immutable.basic;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println("Before:" + immutable);

      // LOTS OF BUSINESS LOGIC HERE
      imagineDragons(immutable);

      System.out.println("After: " + immutable);
   }

   private static void imagineDragons(Immutable immutable) {
      // 1000 loc de biz logic horror. d-ala de intrebi bizu si el zice je ne sais pas

      immutable.getNumbers().add(-1); // face degeaba add. isi ia tzeapa
      System.out.println("ma astept ca intoarce: " +
                         immutable.getNumbers().contains(-1));

      int xuMeu = 7;
      //      immutable.getNumbers().contains(xuMeu);
      //      immutable.getNumbers().stream();
      //      for (int i = 0; i < immutable.getNumbersCount(); i++) {
      //      }
      //      immutable.
   }
}

// unei clase imutabile nu ii poti schimba starea dupa ce ai instantiat-o

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }

   public int getX() {
      return x;
   }

   // 1) prea mult?
   //   public Integer getNumber(int index) {
   //      return numbers.get(index);
   //   }
   //   public int getNumbersCount() {
   //      return numbers.size();
   //   }

   // 2) ii dam o copie
   // - misleading pt client: de ce il lasi sa creada ca poate modifica?
   // - malloc
   //   public List<Integer> getNumbers() {
   //      return new ArrayList<>(numbers);
   //   }


   // 3) Decorator Pattern (GoF): o instanta care imbraca lista originala,
   // delegand toate apelurile de citire la lista originala,
   // dar blocand apelurile de scriere
   // + nu aloca
   // + crapa clientul, nu-l lasi sa creada ca poate modifica
   public List<Integer> getNumbers() { // pick this for normal cases + @Entity de Hibernate
      return Collections.unmodifiableList(numbers);
   }

   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
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
   //
   //   public void setA(int a) {
   //      this.a = a;
   //   }
}


// de ce clasa sa fie finala
//class DusmanDeClasa extends Immutable {
//
//   private final List<Integer> copiaMea;
//
//   DusmanDeClasa(int x, List<Integer> numbers, Other other) {
//      super(x, numbers, other);
//      this.copiaMea = numbers;
//   }
//
//   public void tioArd(Integer vreauEu) {
//      copiaMea.add(vreauEu);
//   }
//}