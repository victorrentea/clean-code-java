package victor.training.refactoring.inheritance;


class S {
    protected int x;

    void m() {System.out.println(x);}
}
class A extends S {
    private int y;

    void n() {x ++;}
}

class B extends  S{
    private int y;
}
class C extends S {
    void n() {x++;}
}