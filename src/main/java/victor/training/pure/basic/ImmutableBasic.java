package victor.training.pure.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

      immutable.getNumbers().clear();
      // LOTS OF BUSINESS LOGIC HERE

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);
   }
}

class Immutable {
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   public Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   // cea mai raspandita solutie 90% : risk: callerul nu vede clar ca n-ar voie sa modifice > ex la runtime
   public List<Integer> getNumbers() {
      return Collections.unmodifiableList(numbers);
   }
   public int getX() {
      return x;
   }
   // waste de memorie
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
   private int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

   public void setA(int a) {
      this.a = a;
   }
}
