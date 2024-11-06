package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableAdvanced {
  public static void main(String[] args) {
    ImmutableList<Integer> list = Stream.of(1, 2, 3).collect(toImmutableList()); // ArrayList

    Immutable immutable = new Immutable(1, 2, list, new Other(15));
    System.out.println("Before: " + immutable);

    wilderness(immutable);
    immutable.getList().clear();

    System.out.println("After:  " + immutable);
  }

  private static void wilderness(Immutable immutable) {
    // dark, deep logic not expected to change the immutable object x,y
  }
}

@Value // = @Getter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor + fields=final private
class Immutable { // shallow immutable
  Integer x;
  Integer y;
  ImmutableList<Integer> list; // NOT friends with Hibernate, but OK with Jackson, Mongo...
  Other other;
}

record Other(int a) {
}
