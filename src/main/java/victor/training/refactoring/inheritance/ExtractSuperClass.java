package victor.training.refactoring.inheritance;


class S {
    public void method() {

    }
}
class A extends S{
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