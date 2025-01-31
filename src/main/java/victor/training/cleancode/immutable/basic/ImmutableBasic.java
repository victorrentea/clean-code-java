package victor.training.cleancode.immutable.basic;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableBasic {
   public static void main(String[] args) {
      List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(toList());

      Immutable immutable = new Immutable(2, list, new Other(13));

      System.out.println(immutable);

      // LOTS OF BUSINESS LOGIC HERE
      immutable.getList().clear();

      System.out.println(immutable.getList());
      System.out.println(immutable);
   }
}

// unui obiect imutabil nu ii poti schimba starea dupa instantiere
class Immutable { // = shallow immutable
   private final Integer x;
   private final List<Integer> list;
   private final Other other;

   public Immutable(Integer x, List<Integer> list, Other other) {
      this.x = x;
      this.list = list;
      this.other = other;
   }

   public Integer getX() {
      return x;
   }

   public List<Integer> getList() {
      return list;
   }

   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, list, other);
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
