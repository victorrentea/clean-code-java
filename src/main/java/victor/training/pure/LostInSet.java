package victor.training.pure;

import lombok.Data;

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
      childOne.setName("Emma-Simona");

      System.out.println(childOne.hashCode());

      System.out.println(puiiMei.contains(childOne));
   }
}

@Data
class Child {
   private String name;
   private int marimePantof;

   public Child(String name) {
      this.name = name;
   }


}