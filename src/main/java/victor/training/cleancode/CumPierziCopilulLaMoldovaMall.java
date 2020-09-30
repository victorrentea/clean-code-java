package victor.training.cleancode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CumPierziCopilulLaMoldovaMall {
   public static void main(String[] args) {

      Copil childOne = new Copil("Emma");
      Set<Copil> puiiMei = new HashSet<>();


      puiiMei.add(childOne);

      System.out.println(puiiMei.contains(childOne));      System.out.println(puiiMei.contains(childOne));      System.out.println(puiiMei.contains(childOne));
      System.out.println(puiiMei.contains(childOne));
      System.out.println(childOne.hashCode());

      childOne.setName("Emma-Simona");

      System.out.println(puiiMei.contains(childOne));
      System.out.println(childOne.hashCode());

   }

}


class Copil {
   private /*final*/ String name;

   public Copil(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Copil copil = (Copil) o;
      return Objects.equals(name, copil.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }
}