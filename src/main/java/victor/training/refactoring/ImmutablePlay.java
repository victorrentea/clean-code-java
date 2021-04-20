package victor.training.refactoring;

import com.google.common.collect.ImmutableList;

import java.util.Objects;

public class ImmutablePlay {
   public static void main(String[] args) {
//      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Other other = new Other(13);

      final Immutable immutable = new Immutable(2, numbers, other);
//      numbers.clear();

      System.out.println(immutable);

//      immutable.numbers().add(-1);

      for (Integer number : immutable.numbers()) {
         System.out.println("El: " + number);
      }

      Immutable immutableChanged = f(immutable);

      System.out.println(immutable);
   }

   private static Immutable f(Immutable immutable) {
      return immutable.withX(-7);
   }
}

class Immutable {
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;
   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = Objects.requireNonNull(other);
   }
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
   // gtters/ no setter but withers
   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
   }

   public int x() {
      return x;
   }

   // most usual
//   public List<Integer> numbers() {
//      return Collections.unmodifiableList(numbers);
//   }

   // a bit too geek
//   public Iterable<Integer> numbers() {
//      return numbers;
//   }

   public ImmutableList<Integer> numbers() {
      return numbers;
   }



   public Other other() {
      return other;
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
