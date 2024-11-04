package victor.training.cleancode;

public record R(int x) {

  public int g() {
    int b = 2;
    System.out.println("b=" + b);
    return 1 + b + x; // this is how getters look since records (w/o get-)
  }
}
