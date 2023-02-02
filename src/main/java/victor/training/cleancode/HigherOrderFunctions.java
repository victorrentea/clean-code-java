package victor.training.cleancode;

import java.util.function.Function;

public class HigherOrderFunctions {

  // SDD: functia intoarsa tre sa cheme functia originala cu
  // parametrul uppercase!
  public Function<String, Integer> method(Function<String, Integer> f) {
    return string -> f.apply(string.toUpperCase());
  }




  public static void main(String[] args) {
    Function<String, Integer> ff = new HigherOrderFunctions().method(s -> {
      System.out.println(s);
      return 1;
    });
    ff.apply("auch!");
  }
}
