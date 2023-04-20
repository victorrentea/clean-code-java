package victor.training.cleancode.immutable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LostInSet {
   public static void main(String[] args) {
      // UC-123: Loosing you child at the Mall
      Set<Child> set = new HashSet<>();

      Child childOne = new Child("Emma");
      set.add(childOne);

      System.out.println(childOne.hashCode());
   //      childOne.setName("Emma-Simona");
      System.out.println(childOne.hashCode());

      set.add(childOne);

      System.out.println(set.contains(childOne));

      System.out.println("I have : " + set);
   }
}


class Child {
   private final String name; // HASH CODE EQUALS SHOULD ONLY INVOLVE IMMUTABLE FIELDS

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