package victor.training.pure.basic;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

import java.util.Objects;

import static com.google.common.collect.ImmutableList.of;
import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
//      List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
      ImmutableList<Integer> numbers = of(1, 2, 3, 4, 5);

      Immutable immutable = new Immutable(2, numbers, new Other(13));

      System.out.println(immutable);

//      immutable.getNumbers().clear();
      // LOTS OF BUSINESS LOGIC HERE


      Immutable newInstance = immutable.toEvens();

      String s ="aa";
      String trim = s.trim();

      Immutable newInstance2 = immutable.withX(5); // "with"er

      System.out.println(immutable.getNumbers());
      System.out.println(immutable);

      new ObiectCuSetter().setX(1).setY(2);
   }
}

@Data
class ObiectCuSetter {
   private int x;
   private int y;

   public ObiectCuSetter setX(int x) {
      this.x = x;
      return this;
   }
}


@Value
class ImmutableLombok { //Deeply immutable
   @With
   int x;
   ImmutableList<Integer> numbers;
   @NonNull
   Other other;
}
record ImmutableRecord(
   int x,
   ImmutableList<Integer> numbers,
   Other other) {
   public ImmutableRecord {
      Objects.requireNonNull(other);
   }

   public ImmutableRecord withX(int newX) {
      return new ImmutableRecord(newX, numbers, other);
   }
}


class Immutable { //Deeply immutable
   private final int x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   public Immutable(int x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      if (numbers.size()<2) {
         throw new IllegalArgumentException("Prea putine numere");
      }
      this.numbers = numbers;
      this.other = Objects.requireNonNull(other);
   }
   // cea mai corecta dar invaziva:
   public ImmutableList<Integer> getNumbers() {
      return numbers;
   }
   // cea mai raspandita solutie 90% : risk: callerul nu vede clar ca n-ar voie sa modifice > ex la runtime
//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers);
//   }
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

   public Immutable toEvens() {
      ImmutableList<Integer> evens = ImmutableList.copyOf(numbers.stream().filter(n -> n % 2 == 0).collect(toList()));
      return new Immutable(x, evens, other);
   }

   public Immutable withX(int newX) {
      return new Immutable(newX, numbers, other);
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
