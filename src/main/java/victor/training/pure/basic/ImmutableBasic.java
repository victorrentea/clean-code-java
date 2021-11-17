package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.util.Objects;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Immutable immutable = new Immutable(2, numbers,new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      int newX = someCreepyMethod(immutable);

      immutable = immutable.withX(newX);

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);

      new ImmutableLombok(1, null, new Other(1));
   }

   private static int someCreepyMethod(Immutable immutable) {
      // WHAT CAN GO WRONG HERE?
//      immutable.getNumbers().add(-10);


      return -1;
   }
}


@Value
class ImmutableLombok {
   @With
   int x;
   @NonNull // not sure
   ImmutableList<Integer> numbers; // HIBERNATE HAS ALLERGY TO THIS, but ok for Mongo/Cassandra/...nosql
   Other other;
}


@ToString
class Immutable { //deep immutable object
   private final int x;
   private final ImmutableList<Integer> numbers; // HIBERNATE HAS ALLERGY TO THIS, but ok for Mongo/Cassandra/...nosql
   private final Other other;

   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      // what validation to enoforce on my fields
      this.x = x;
      this.numbers = Objects.requireNonNull(numbers);
      this.other = other;
   }

   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
   }


   public int getX() {
      return x;
   }
//   public List<Integer> getNumbers() {
////      return new ArrayList<>(numbers); //wasteful for memory
//      return Collections.unmodifiableList(numbers); // 95% of teams do this if they want immutability
//   }
//   public Iterable<Integer> getNumbers() { // weird but possible
//      return numbers;
//   }

   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }

   public Other getOther() {
      return other;
   }
}

@ToString
class Other {
   private final int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }
}
