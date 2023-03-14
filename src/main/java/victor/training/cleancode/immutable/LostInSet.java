package victor.training.cleancode.immutable;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LostInSet {
   public static void main(String[] args) {
      // UC-123: Loosing you child at the Mall
      Set<Child> set = new HashSet<>();

      Child childOne = new Child("Emma");
      set.add(childOne);

      System.out.println(set.contains(childOne));

      // adolescenta

      System.out.println("hash1=" + childOne.hashCode());
      //      childOne.setName("Emma-Simona");
      System.out.println("hash2=" + childOne.hashCode());
      System.out.println(set.contains(childOne));

      set.add(childOne);
      System.out.println("Cati copii am ? " + set.size());


   }
}


//@Data @Entity
class Child {
   //   @GeneratedValue
   //  @Id
   //   Long id;
   private final String name;

   public Child(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   //   public void setName(String name) {
   //      this.name = name;
   //   }

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