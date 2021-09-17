package victor.training.pure;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LostInSet {
   public static void main(String[] args) {
      // UC-123: Loosing you child at the Mall
      Set<Child> puiiMei = new HashSet<>();

      Child childOne = new Child("Emma");
      puiiMei.add(childOne);


      System.out.println(childOne.hashCode());
      System.out.println(puiiMei.contains(childOne));
//      childOne.setName("Emma-Simona");

      System.out.println(childOne.hashCode());

      System.out.println(puiiMei.contains(childOne));
   }
}


class Child {
   private final String name;
   private int marimePantof;

   public int getMarimePantof() {
      return marimePantof;
   }

   public void setMarimePantof(int marimePantof) {
      this.marimePantof = marimePantof;
   }

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