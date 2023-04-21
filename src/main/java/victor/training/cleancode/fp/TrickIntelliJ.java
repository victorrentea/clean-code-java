package victor.training.cleancode.fp;

import org.springframework.web.bind.annotation.GetMapping;

public class TrickIntelliJ {

  public void unused() {
    System.out.println("Call bo$$");
  }

  public static void main(String[] args) {

    // - unused is a library function called by another project that imports this jar i'm builder
    // - reflection
//    Class.forName("victor.training.cleancode.fp.Trick"+"IntelliJ").getMethod("unused").invoke();
  }
}
