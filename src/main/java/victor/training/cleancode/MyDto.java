package victor.training.cleancode;


public class MyDto {
  private int a;
  private int b;

  public void setA(int a) {
    this.a = a;
  }
}

class Altundeva {

  private MyDto method(MyDto param) {
    param.setA(1);
    return param; // daca intorci obiect, atunci semnatura metodei sugereaza ca functia e PURA (ca nu modifica param), cf CQS
    // lucru fals insa aici.

//    daca vrei sa fii corect/
//    return new MyDto(vechile date, dar a =1)
  }

  public void altaMetoda() {

    MyDto d = new MyDto();
    MyDto altaInstanta = method(d);

  }
}


