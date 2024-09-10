package victor.training.cleancode;


import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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



record R(int x) {}

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    R r = new R(3);
    int altundeva = two.gInternDeCareNimeniSaNuStie(r.x(), i -> {
    }); // cazul meu fara PRINT
    return 2 * two.g(r.x());
  }
}
class Two {
  public int g(int x) {
    return gInternDeCareNimeniSaNuStie(x, i -> System.out.println("i=" + i));
  }

  public int gInternDeCareNimeniSaNuStie(int x, Consumer<Integer> lambdica) {
    int b = 2;
    lambdica.accept(x);
    return 1 + b + x;
  }

  public void unknown() {
    System.out.println("b=" + 987);
  }
}
