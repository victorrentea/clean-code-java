package victor.training.fp;

import org.springframework.beans.factory.annotation.Autowired;

public class UnServiciu {
  private FRepo fRepo;


  public void g() {
    f(fRepo.findById(1));
  }

  private void f(F f) {
//    F f = f;
    System.out.printf("Logica cu f: " + f);
    System.out.printf("Logica cu f: " + f);
    System.out.printf("Logica cu f: " + f);
    System.out.printf("Logica cu f: " + f);
  }
}

interface FRepo {

  F findById(int id);
}
class F{}