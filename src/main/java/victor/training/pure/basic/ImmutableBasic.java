package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import lombok.With;
import org.hibernate.collection.internal.PersistentBag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13) );
numbers.clear();

      System.out.println(immutable);

      canYouImagine5000LinesOfCodeThatComputeThePriceForYourShoppingCart(immutable); //=> YOU DIE

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static void canYouImagine5000LinesOfCodeThatComputeThePriceForYourShoppingCart(Immutable immutable) {
//      immutable.getNumbers().clear();
   }
}

@Value
class Immutable2 {
   int x;
   ImmutableList<Integer> numbers; // best but not hibernate
   Other other;
}

//@ENtity // never
class Immutable {
   @With
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = new ArrayList<>(numbers);
      this.other = Objects.requireNonNull(other);
   }
   public int getX() {
      return x;
   }
   public List<Integer> getNumbers() { // best for hibernate entities that want to guard their internal collections
      return Collections.unmodifiableList(numbers);
   }
//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);
//   }
   public Other getOther() {
      return other;
   }
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
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
