package victor.training.pure;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LostInSet {
   public static void main(String[] args) {
      // UC-123: Loosing you child at the Mall
      Set<Child> puiiMei = new HashSet<>();

      Child childPoc = new Child("Emma");
      puiiMei.add(childPoc);

      System.out.println(puiiMei.contains(childPoc));

      System.out.println(childPoc.hashCode());

//      childPoc.setName("Emma-Simona");

      System.out.println(childPoc.hashCode());
      System.out.println(puiiMei.contains(childPoc));
   }
}


class Child {
   private final String name;

   public Child(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Child child = (Child) o;
      return Objects.equals(name, child.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }
}