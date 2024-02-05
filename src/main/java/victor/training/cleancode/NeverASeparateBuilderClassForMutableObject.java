package victor.training.cleancode;

import lombok.Data;

public class NeverASeparateBuilderClassForMutableObject {
  public static void main(String[] args) {
    X x = new X()
        .setA(1) //
        .setB(2);
  }
}


@Data // !mind the lombok.config
class X {
  int a;
  int b;
}