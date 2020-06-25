package victor.training.cleancode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CuCopiluLaHipermarket {
   public static void main(String[] args) {
      Copil childOne = new Copil("Emma");

      Set<Copil> puiiMei = new HashSet<>();
      puiiMei.add(childOne);

      System.out.println(childOne.hashCode());
      System.out.println(puiiMei.contains(childOne));

//      childOne.setName("Emma-Simona");
//      childOne.setAltNume("Emma-Simona");
      System.out.println(childOne.hashCode());
      System.out.println(puiiMei.contains(childOne));
   }
}

//class CopilHackuit extends Copil {
//   private String altNume;
//
//   public CopilHackuit(String name) {
//      super(name);
//   }
//
//   @Override
//   public String getName() {
//      return altNume;
//   }
//
//   public CopilHackuit setAltNume(String altNume) {
//      this.altNume = altNume;
//      return this;
//   }
//}

class Copil {
   private final String name;

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Copil copil = (Copil) o;
      return Objects.equals(name, copil.name);
   }

   public Copil(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

//   public Copil setName(String name) {
//      this.name = name;
//      return this;
//   }

   @Override
   public String toString() {
      return "Copil{" +
          "name='" + name + '\'' +
          '}';
   }
}
