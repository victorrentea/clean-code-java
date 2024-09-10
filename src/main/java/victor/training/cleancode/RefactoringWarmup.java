package victor.training.cleancode;


import java.util.Arrays;
import java.util.List;

class RefactoringWarmup {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
    loop();
  }

  private static void loop() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    double ssq = 0;
    for (Integer number : numbers) {
      if (number % 2 == 0) {
        ssq += number * number;
      }
    }
    System.out.println(Math.sqrt(ssq));
  }
}


record R(int x) {
}

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    R r = new R(3);
    int altundeva = two.g(r.x(), false); // cazul meu fara PRINT, aici NU
    return 2 * two.g(r.x(), true); // aici DA
  }
}

class Two {

  /** @deprecated please use {@link Two#g(int, boolean)} (int,boolean)} instead*/
  @Deprecated(since = "1.6", forRemoval = true)
  public int g(int x) {
    return g(x, true);
  }
  public int g(int x, boolean cuPrint) {
    int b = 2;
    if (cuPrint) {
      System.out.println("b=" + b);
    }
    return 1 + b + x;
  }

  public void unknown() {
    System.out.println("b=" + 987);
  }
}
