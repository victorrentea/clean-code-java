package victor.training.cleancode;

// TODO 5
class ClassA {
  public void f(int n) {
    System.out.println("F Logic");
    for (int i = 0; i < 4; i++) {
      if (n + i < 0) {
        System.out.println("CodeO" + i);
      } else {
        throw new IllegalArgumentException();
      }
    }
  }
}

class ClassB {
  public void g(int n) {
    System.out.println("G Logic");
    try {
      for (int j = 0; j < 4; j++) {
        if (n + j < 0) {
          System.out.println("Code0" + j);
        } else {
          throw new IllegalArgumentException();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Rethrow", e);
    }
  }

}