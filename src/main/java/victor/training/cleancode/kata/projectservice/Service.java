package victor.training.cleancode.kata.projectservice;

public class Service {
   private String name;

   public String getName() {
      return name;
   }

   public Service setName(String name) {
      this.name = name;
      return this;
   }
}
