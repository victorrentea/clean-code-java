package victor.training.cleancode.immutable.basic;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ImmutableBasic {
  public static void main(String[] args) {
    ImmutableList<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toImmutableList());

    Immutable immutable = new Immutable(2, 3, numbers, new Other(13));

    System.out.println(immutable);

    Immutable immutableMutat =  patru(immutable);

    System.out.println(immutableMutat.getNumbers());
    System.out.println(immutableMutat);
  }

  private static Immutable patru(Immutable immutable) {
    return trei(immutable);
  }

  private static Immutable trei(Immutable immutable) {
    return doi(immutable);
  }

  private static Immutable doi(Immutable immutable) {
    return horror(immutable);
  }

  private static Immutable horror(Immutable immutable) {
    // sa mute curieru pe harta (x,y)
    var newX = immutable.getX() + 1;
    var newY = immutable.getY() + 1;

//    return immutable.withX(newX).withY(newY); // ne-semantic. nu are "SENS"
    return immutable.withPosition(newX, newY);// mai destep, are 'motivatie'
//    return immutable.withPosition(new Position(newX, newY));// am descoperit un nou concept (Value Object)
//    return immutable.moveTo(new Position(newX, newY));// nume mai bune
  }

}

@Value // = @Getter + @ToString + @EqualsAndHashCode + @AllArgsConstructor
// + all fields are final private
// "shallow" immutable nu "DEEP"
class Immutable {
  Integer x;
  Integer y;
  ImmutableList<Integer> numbers; // daca nu ai ORM/JPA e da best!
  Other other;

  // Witheri
  public Immutable withPosition(int newX, int newY) {
    return new Immutable(newX, newY,
        numbers,
        other);
  }

//   public List<Integer> getNumbers() {
//      // pt @Entity; Decorator Pattern™️ care blocheaza scrierile pe lista "imbracata"
//      return Collections.unmodifiableList(numbers);
//   }

  //   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers);
//      // - malloc mult
//      // - clientul chiar poate sa creada ca a sters din lista (fraeru)
//   }

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
