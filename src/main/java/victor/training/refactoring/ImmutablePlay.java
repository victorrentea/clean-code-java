package victor.training.refactoring;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Other other = new Other(13, 12);
      Immutable immutable = new Immutable(2, numbers, other);

      System.out.println(immutable);


      Immutable immutable2 = immutable.addNumber(1)
         .addNumber(12)
         .addNumber(13)
         .addNumber(14)
         .addNumber(15);


//      String s = "a";
//      s = s.toUpperCase();

//      immutable.getNumbers().clear();

      // LOTS OF BUSINESS LOGIC HERE
      System.out.println(immutable);
//      immutable.getOther().withA()
      System.out.println("changed " + immutable2);
   }
}

@Value
class Immutable {
   int x;
   List<Integer> numbers;
   @NonNull
   Other other;


   public Immutable addNumber(int i) {
      List<Integer> newList = new ArrayList<>(numbers);
      newList.add(i);
      return new Immutable(x, newList, other);
   }

   public List<Integer> getNumbers() {
      return unmodifiableList(numbers); // THE better solution is to use UnmodifiableList from guava colection
   }
   // waste of memory
//   public OtherMutable getOther() {
//      return new OtherMutable(otherMutable);
//   }
}

@Value
class Other {
   @With
   int a;
   int b;
}
