package victor.training.cleancode.fp;

import java.util.function.Supplier;

public class LoanPattern_vsAOP {

  public static void main(String[] args) {
    timeIt(() -> suspect());
  }

  private static int suspect() {
    System.out.println("Stuff");
    return 1;
  }

  private static <T> T timeIt(Supplier<T> f) {
    long t0 = System.currentTimeMillis();
    T r = f.get();
    long t1 = System.currentTimeMillis();
    System.out.println("Collect metric: " + (t1 - t0));
    return r;
  }
}
