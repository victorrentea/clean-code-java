package victor.training.cleancode.exception;

import java.io.IOException;

public class CustomRuntimeEx1 extends RuntimeException{
  public CustomRuntimeEx1(IOException e) {
    throw new RuntimeException("Method not implemented");
  }
}
