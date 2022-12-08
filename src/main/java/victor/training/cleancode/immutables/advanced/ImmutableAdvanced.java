package victor.training.cleancode.immutables.advanced;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3);

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      // wilderness
      Immutable changedImmutable = codHorror(immutable);
      //a)  120 ifuri in total sub functia => 120 teste. eg ComputePrice(ShoppingCart), ComputeDamage(HeroInventory)
      //b) multi threading/reactive -> sa eviti race conditions


      System.out.println("After: " + changedImmutable); //TAKEME
   }

   private static Immutable codHorror(Immutable immutable) {
//      immutable.getOther().setA(1);
//      immutable.getNumbers().add(99); + hint da' dup-aia il scot, da uita.
      return immutable.withX(99);
   }
}

@Value // e mai bun ca @Data ca face campurile "private final" automat
class Immutable {
    @With
    int x;
    ImmutableList<Integer> numbers;
    @NonNull // prea mult? sara exceptie din cod invizibil generat?
    Other other;
//
//   public Immutable withX(int newX) { // "withers"
//      return new Immutable(newX, numbers, other);
//   }
}

// java 17
// record Immutable(int x, ImmutableList<Integer> numbers, Other other) { // deep immutable acum
//
//   public Immutable withX(int newX) { // "withers"
//      return new Immutable(newX, numbers, other);
//   }
//}


//class Immutable { // deep immutable acum
//   private final int x;
//   private final ImmutableList<Integer> numbers;
//   private final Other other;
//
//   Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//      this.x = x;
//      this.numbers = numbers;
//      this.other = other;
//   }
//
////   public List<Integer> getNumbers() {
////      // RAU pt ca malloc la fiecare get si
////      // RAU pt ca saracu care face ADD e inselat > ma doar in *** de cine ma cheama
////      return new ArrayList<>(numbers);
////   }
//
//   // bun, traditional:
////   public List<Integer> getNumbers() {
////      return Collections.unmodifiableList(numbers); // Decorator Design Pattern = iti inapoi o ALTA implementare
////      // a interfetei List care delega toate citirile la lista originala dar BLOCHEAZA (EX) orice incercare de scriere
////   }
//
//   // prea geek
////   public Iterable<Integer> getNumbers() {
////      return numbers;
////   }
//
//   public ImmutableList<Integer> getNumbers() {
//      return numbers;
//   }
//
//   public int getX() {
//      return x;
//   }
//   public Other getOther() {
//      return other;
//   }
//
//   @Override
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
//   }
//
//   public Immutable withX(int newX) { // "withers"
//      return new Immutable(newX, numbers, other);
//   }
//}

class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }
}
