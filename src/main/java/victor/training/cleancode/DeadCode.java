package victor.training.cleancode;

public class DeadCode { // ! la lib
  public void publica() { // ! la lib
    System.out.println("chestii");
  }

  private void privata() { // ! reflection
    System.out.println("chestii");
  }

  private void met(int p) { // ! reflection (RAUTATE, 90's style)
    int i = 1;// 100% safe de dilit
  }
}
