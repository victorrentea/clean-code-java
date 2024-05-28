package victor.training.cleancode;


class PlayGround {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
  }
}

record R(int x) {
  public int g(int f, boolean boulean) {
    if(boulean) System.out.println("b =" + f);
    int destept = 1;
    return destept + f + x();
  }
}

class One {
  private final Two two;
  One(Two two) {
    this.two = two;
  }

  public int f() {
    new R(3).g(3, false);
    return 2 * new R(3).g(3, true);
  }
}
//class II {}

class Two {

  public void unknown() {
    System.out.println("b=" + 987);
  }
}
