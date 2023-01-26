package victor.training.cleancode.fp;

import java.util.function.Consumer;

public class Panica {


  public static void main(String[] args) {
    String  s ="16";
    Consumer<String> x = method(Panica::radical);
    x.accept(s);
  }
  public static void radical(int x) {
    System.out.println(Math.sqrt(x));
  }

  /**
   * @param ff o functie care accepta un integer
   * @return o functie X care accepta un string,
   *  cand e chemata X, va chema ff cu param convertit la int
   */
  public static Consumer<String>  method(Consumer<Integer> ff) {
    return s -> ff.accept(Integer.parseInt(s));
  }
}
