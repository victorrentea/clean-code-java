package victor.training.cleancode.immutable.basic;

import com.google.common.collect.ImmutableList;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toImmutableList());
      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      // nu reatribui variabile (Confused variable code smell).
      // ==> faci altele noi!
      Immutable immutable6 = wilderness(immutable);
     for (int i = 0; i < 10_000; i++) {
        immutable6 = wilderness(immutable6);
     }

      System.out.println(immutable6);
   }

   private static Immutable wilderness(Immutable immutable) {
      // LOTS OF BUSINESS LOGIC HERE

     return immutable.withAnExtraNumber(6);
   }

}

record Immutable(
    Integer x,
    ImmutableList<Integer> numbers,
    Other other) {

  // incapsulat "modificare" - clonarea de fapt
  public Immutable withAnExtraNumber(int element) { // "with"-eri ~ setteri/getteri
    ImmutableList<Integer> newList = new ImmutableList.Builder<Integer>()
         .addAll(numbers)  // Add all elements from the original list
         .add(element)      // Add new element(s)
         .build();

    return new Immutable(x, newList, other);
  }

}
//class Immutable {
//   private final Integer x;
//   private final ImmutableList<Integer> numbers; // ..Set, ..Map. ATENTIE: JPA/Hibernate nu poate pt ca vrea sa-si puna PersistentBag implements List, PersistentSet implemenets Set
//   private final Other other;
//   public Immutable(Integer x, ImmutableList<Integer> numbers, Other other) {
//      this.x = x;
//      this.numbers = numbers;
//      this.other = other;
//   }
//   public Integer getX() {
//      return x;
//   }
//   public ImmutableList<Integer> getNumbers() {
////      return Collections.unmodifiableList(numbers); // #1 istoric prima solutie sa repare colectiile mutabile din Java
//      return numbers; // #1 istoric prima solutie sa repare colectiile mutabile din Java
//   }
//   public Other getOther() {
//      return other;
//   }
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
//   }
//}

