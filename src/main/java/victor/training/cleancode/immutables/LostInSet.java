package victor.training.cleancode.immutables;

import lombok.Data;
import org.checkerframework.checker.interning.qual.InternedDistinct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class LostInSet {
    public static void main(String[] args) {
        // UC-123: Loosing you child at the Mall
        Set<Child> set = new HashSet<>();

        Child childOne = new Child("Emma");
        set.add(childOne);
        System.out.println("Next to me: " + set.contains(childOne));

        System.out.println(childOne.hashCode());
        childOne.setName("Emma-Simona"); // hibernate comes and says id = SEQ.nextval
        System.out.println(childOne.hashCode());

//       Child childTwo = new Child("Emma");
        System.out.println("Next to me: " + set.contains(childOne));
    }
}
// THe fields involved in hashCode/equals should never change in the life of an object.
//@Entity
//@Data
class Child {
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String id = UUID.randomUUID().toString();
    private String name;

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//   public Long getId() {
//      return id;
//   }
//
//   public void setId(Long id) {
//      this.id = id;
//   }


   public void setName(String name) {
      this.name = name;
   }

//   @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Child child = (Child) o;
//        return Objects.equals(name, child.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
}