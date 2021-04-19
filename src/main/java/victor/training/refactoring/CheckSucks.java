package victor.training.refactoring;

import java.net.MalformedURLException;
import java.net.URL;

public class CheckSucks {

//   @GET
   public void uiFacingFunc(String userInput) {
      try {
         method2(userInput);
      } catch (MalformedURLException e) {
         System.out.println("Neah. was wrong. enter again");
      }
   }



   public void method2(String userInput) throws MalformedURLException { // innocent soul
      method(userInput);
   }




   public void method(String userInput) throws MalformedURLException {
      URL url = new URL(userInput);
      System.out.println(url);

   }
}
