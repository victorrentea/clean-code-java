package victor.training.cleancode.immutables.basic;

import com.google.common.collect.ImmutableList;
import lombok.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, ImmutableList.copyOf(numbers), new Other(13));

      System.out.println(immutable);
//      numbers.clear(); // bo!!

      dark1000KbizLogic(immutable);
      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

   private static void dark1000KbizLogic(Immutable immutable) {
      Immutable changedCopy = layersBelow(immutable);
      // later
      System.out.println("Some logic that is fixed is x++ " + changedCopy);
   }

   private static Immutable layersBelow(Immutable immutable) {
      // bug fix
//      immutable.getOther().setX
//      immutable.getNumbers().add(-1); // deprecated now!! + exception
      Immutable changedCopy = immutable.withX(immutable.getX() + 1);
      return changedCopy;
   }

}
// Lombok
// "immutable" = you can't change after you instantiated it
//@Getter
//@ToString
//@AllArgsConstructor

//@Data // i normally avoid. instead, i love @Value
//final class Immutable {
//   @With
//   private final int x;
//   private final ImmutableList<Integer> numbers;
//   private final Other other;
//}

@Value // ❤️
class Immutable {
   @With
   int x;
   ImmutableList<Integer> numbers;
   Other other;
}
//// java standard solution
//// "immutable" = you can't change after you instantiated it
//final class Immutable {
//   private final int x;
//   private final ImmutableList<Integer> numbers;
//   private final Other other;
//
//   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//      this.x = x;
//      this.numbers = numbers;
//      this.other = other;
//   }
//
//   public Immutable withX(int newX) { // "with"er
//      return new Immutable(newX, numbers, other);
//   }
//
//   public int getX() {
//      return x;
//   }
//   public ImmutableList<Integer> getNumbers() {
////      return new ArrayList<>(numbers); // #1 malloc / free  inefficient + lying: the client adding to this list MIGHT HAVE the impressiion that it's changing my list. no exeption.
////      return Collections.unmodifiableList(numbers); // #2 you return a Decorator™️ Pattern over the original list that blocks any attempt to mutate the list. common in hibernate entities
//      return numbers; // #3 give up Java Collections and use Guava; - hibernate has allergy to this + works with Mongo, Cassandra, MyBatis, Jackson, JdbcTemplate + manual extract
//   }
//   public Other getOther() {
//      return other;
//   }
//
//   public String toString() {
//      return String.format("Immutable{x=%d, numbers=%s, other=%s}", getX(), getNumbers(), getOther());
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
