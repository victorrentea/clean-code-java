package victor.training.cleancode.fp;

import java.util.List;

public class ThisIsBad {
  private List<String> strings;
//  private List<String> strings = new ArrayList<>(); // good


  {
    method(null);
  }

  public void method(List<String> list) {
  }
}
