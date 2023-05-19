package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import lombok.With;

import java.util.stream.Stream;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = Stream.of(1, 2, 3).collect(ImmutableList.toImmutableList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      Immutable immutable2 = wilderness(immutable); // 500 de linii de logica horror

      System.out.println("After:  " + immutable2);
   }

   private static Immutable wilderness(Immutable immutable) {
      // dark deep logic
//      immutable.getNumbers().add(-1);
//      immutable.getNumbers().contains(-1)
      return immutable.withX(17);
   }
}
class Immutable { // DEEP IMMUTABLE!
   @With
   private final Integer x;
   private final ImmutableList<Integer> numbers; // renunti la colelction java si mergi la guava
   private final Other other;

   Immutable(Integer x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
//      this.numbers = List.copyOf(numbers); // malloc la new; nu e Hibernate friendly
      this.other = other;
   }
//   public List<Integer> getNumbers() { // hibernate friendly
//      return Collections.unmodifiableList(numbers); // decorator pattern peste lista originala care blocheaza orice mutatie
//   }
//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // Naspa pt ca: 1) malloc 2) misleading pt client (poate crede ca amodificat)
//   }
   public ImmutableList<Integer> getNumbers() {
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
@Value // = @Data + toate campurile  private final
class Other {
   int a;
}

//record Other2(int a) { // Java 17
//}
