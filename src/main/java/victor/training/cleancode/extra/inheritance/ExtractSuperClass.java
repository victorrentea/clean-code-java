package victor.training.cleancode.extra.inheritance;


class S {}
class A {
    private int x;
    private int y;
    void m() {System.out.println(x);}
    void n() {x ++;}
}

class B {
    private int x;
    private int y;
    void m() {System.out.println(x);}
}
class C {
    private int x;
    void m() {System.out.println(x);}
    void n() {x++;}
}