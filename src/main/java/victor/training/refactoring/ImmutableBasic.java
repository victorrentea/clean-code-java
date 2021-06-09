package victor.training.refactoring;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other(13));
//

      System.out.println(immutable);

      numbers.clear();

//      immutable.getNumbers().clear();


      for (Integer number : immutable.getNumbers()) {
         System.out.println(number);
      }

//      immutable.getOther().setA(-6);

      // LOTS OF BUSINESS LOGIC HERE


      System.out.println(immutable);
//      System.out.println();

      Immutable immutable999 = immutable.withX(999);
   }
}

//record Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//
//   public Immutable withX(int newX) {
//      return new Immutable(newX, numbers, other);
//   }
//}

//@Value
//class Immutable {
//   @With
//   int x;
//   List<Integer> numbers;
//   @NonNull
//   Other other;
//
//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers);
//   }
//}



class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = Objects.requireNonNull(other);
   }

   public int getX() {
      return x;
   }

   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }
   //   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);
//   }
//   public ImmutableList<Integer> getNumbers() {
//      return numbers;
//   }
   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
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


   @Override
   public String toString() {
      return "Other{" +
             "a=" + a +
             '}';
   }
}
