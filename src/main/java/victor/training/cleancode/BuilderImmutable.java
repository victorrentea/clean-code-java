package victor.training.cleancode;

import lombok.Builder;
import lombok.Value;

public class BuilderImmutable {
  public static void main(String[] args) {
    new BigImmutable(1, 2, 3, 4, 5, 6);
    // Builder in java fixes a language problem, the lack of "named parameters".

    BigImmutable bigImmutable = BigImmutable.builder()
//        .a(1)
//        .b(2)
        .ab(new AB(1, 2))
        .c(3)
        .d(4)
        .e(5)
        .f(6)
        .build();
    // before you throw in a Builder consider breaking the immutable object into smaller immutable objects

    f(bigImmutable);
    System.out.println(bigImmutable);
  }

  private static BigImmutable f(BigImmutable bigImmutable) {
//    BigImmutable modifiedClone = bigImmutable.toBuilder()
//        .a(10) // back to bad habits
//        .b(20) // if A and B change together, should't they be a separate object record AB(a,b)
//        .build();
     BigImmutable modifiedClone = bigImmutable.withAB(new AB(10, 20));
    return modifiedClone;
  }
}

@Value // 👈 Immutable @Data (no setters) only getter and constructor and equals/hashCode and toString
@Builder// (toBuilder = true)
class BigImmutable {
 AB ab;
  int c;
  int d;
  int e;
  int f;

  public BigImmutable withAB(AB ab) {
    return new BigImmutable(ab, c, d, e, f);
  }
}

record AB(int a, int b) { }