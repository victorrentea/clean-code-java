package victor.training.cleancode;

import lombok.Builder;
import lombok.Value;

public class BuilderImmutable {
  public static void main(String[] args) {
    new BigImmutable(1, 2, 3, 4, 5, 6);
    // Builder in java fixes a language problem, the lack of "named parameters".

    BigImmutable bigImmutable = BigImmutable.builder()
        .a(1)
        .b(2)
        .c(3)
        .d(4)
        .e(5)
        .f(6)
        .build();
    // before you throw in a Builder consider breaking the immutable object into smaller immutable objects

    System.out.println(bigImmutable);
  }
}

@Value // 👈 Immutable @Data (no setters) only getter and constructor and equals/hashCode and toString
@Builder
class BigImmutable {
  int a;
  int b;
  int c;
  int d;
  int e;
  int f;
}