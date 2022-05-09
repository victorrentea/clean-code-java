package victor.training.cleancode;

public class ClasaMare {

    private final victor.training.cleancode.AB AB = new AB();
    private String c; // x 600

    public String getA() {
        return AB.getA();
    }

    public void setA(String a) {
        AB.setA(a);
    }

    public String getB() {
        return AB.getB();
    }

    public void setB(String b) {
        AB.setB(b);
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}

class Client {
    public void method(ClasaMare c) {
        System.out.println(c.getA() + " " + c.getB());
    }
}
