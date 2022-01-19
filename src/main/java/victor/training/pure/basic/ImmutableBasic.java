package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;

public class ImmutableBasic {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3, 4, 5);

      Immutable immutable = new Immutable(2, 5, numbers, new Other(13));

      System.out.println(immutable);

//      immutable.getNumbers().clear();

      Immutable immutable1 = immutable.withX(3);
//      Immutable immutable1 = new Immutable.Builder(immutable).withX(3).build();

      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }

}

class Immutable {
   private final int x;
   private final int y;
   private final ImmutableList<Integer> numbers;
   private final Other other;
   public Immutable(int x, int y, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.y = y;
      this.numbers = numbers;
      this.other = other;
   }

   public Immutable withX(int newX) { // wither
      return new Immutable(newX, y, getNumbers(), getOther());
   }

   public int getX() {
      return x;
   }
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
