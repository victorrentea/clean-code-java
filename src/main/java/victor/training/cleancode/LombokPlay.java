package victor.training.cleancode;

import lombok.Data;

public class LombokPlay {
  public static void main(String[] args) {
    var x = new X().setFirstName("a").setLastName("b");
  }
}

@Data
class X {
  private String firstName;
  private String lastName;
}
