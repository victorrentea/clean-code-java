package victor.training.cleancode.fp;

import java.util.function.Consumer;

public class InterviewQuestion {


  // return a function than when called delegates to ff
  // upercasing the parameter
  public Consumer<String> f(Consumer<String> ff) {
    return string -> ff.accept(string.toUpperCase());
  }


  public static void main(String[] args) {
    Consumer<String> r = new InterviewQuestion()
            .f(System.out::println);
    r.accept("aa");
  }
}
