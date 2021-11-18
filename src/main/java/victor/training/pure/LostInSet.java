package victor.training.pure;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LostInSet {
   public static void main(String[] args) {
      // UC-123: Loosing you child at the Mall
      Set<Child> set = new HashSet<>();

      Child childPoc = new Child("Emma");
      set.add(childPoc);

      System.out.println(set.hashCode());
      System.out.println(set.contains(childPoc));

      // teenage time
//      childPoc.setName("Emma-Simona");

      System.out.println(set.hashCode());
      System.out.println(set.contains(childPoc));
   }
}


//@Data
//@Entity
class Child {
//   @Id
//   private Long id;
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

//   @Override
//   public boolean equals(Object o) {
//      if (this == o) return true;
//      if (o == null || getClass() != o.getClass()) return false;
//      Child child = (Child) o;
//      return Objects.equals(name, child.name);
//   }
//
//   @Override
//   public int hashCode() {
//      return Objects.hash(name);
//   }
}