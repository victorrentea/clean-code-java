package victor.training.cleancode.fp;

public class Pure2 {
  public static int method(D d) {
    int i = d.x;
    d.x++;
    return i;
  }

  public static void main(String[] args) {
    D d = new D();
    d.x = 1;
    int r = method(d);
    System.out.println("r:" + r);
    System.out.println("state of d: " + d.x);
  }

  static class D {
    int x;
  }
}
