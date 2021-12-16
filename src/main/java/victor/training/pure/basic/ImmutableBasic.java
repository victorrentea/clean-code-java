package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      Immutable newChangedObj = innocent(immutable);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static Immutable innocent(Immutable immutable) {
//      immutable.getNumbers().clear(); // thte caller might not know about the runtie error
      return immutable.withX(-1);
   }

}

//@Value  // = DATA + private final all fields
class Immutable {
   @With
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }

   public int getX() {
      return x;
   }

//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);
//   }
//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers);
//   }
   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }

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
