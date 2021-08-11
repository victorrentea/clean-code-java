package victor.training.cleancode;

import lombok.RequiredArgsConstructor;

public class ClientClass {
   private  ISomeClass someClass;

   public void method() {

      someClass.method1();
   }
}
