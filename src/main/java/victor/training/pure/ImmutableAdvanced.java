package victor.training.pure;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.With;
import org.junit.Test;
import victor.training.pure.Immutable.ImmutableBuilder;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      ImmutableList<Integer> numbers = ImmutableList.of(1, 2, 3);

      Immutable immutable = new Immutable(1, numbers, new Other(15));

      System.out.println(immutable);
//      numbers.clear();
//      immutable.getNumbers().clear();
//      for (i=1..1M) {
      Immutable immutable2 = immutable.withX(2);
//      }
      System.out.println(immutable);

   }

   public static ImmutableBuilder aValidImmutable() {
      return Immutable.builder().x(1)
          .numbers(ImmutableList.of(1))
          .other(new Other(1));
//      return new Immutable.builder().(1, ImmutableList.of(), new Other(2),1, ImmutableList.of(), new Other(2));
   }

   @Test
   public void test() {
      Immutable data = aValidImmutable()
          .x(1)
          .build();
      someProd(data);
   }

   private void someProd(Immutable data) {

   }
}


//@Data
//class A {
//   private String a;
//   private String b;
//
//   static {
//      new A().setA("a").setB("b");
//   }
//}

@Builder
@Value
class Immutable {
   @With
   int x;
   ImmutableList<Integer> numbers;
   @NonNull
   Other other;
//   public Immutable withX(int newX) {
//      return new Immutable(newX, numbers, other);
//   }
}

// I had a dream;... Java 17

//record Immutable(int x, ImmutableList<Integer> numbers, Other other) {
//
//   public Immutable withX(int newX) {
//      return new Immutable(newX, numbers, other);
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
