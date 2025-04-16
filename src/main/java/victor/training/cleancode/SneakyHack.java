package victor.training.cleancode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SneakyHack {

  public static void method() throws FileNotFoundException {
//    try {
    new FileOutputStream("a");
//    } catch (FileNotFoundException e) {
//      throw new RuntimeException(e);
//    }
  }

  public void caller() {
    try {
      method();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
