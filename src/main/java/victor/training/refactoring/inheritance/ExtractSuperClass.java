package victor.training.refactoring.inheritance;


class S {
    protected int x;

    void m() {System.out.println(x);}
}

class A extends S {

    protected int y;

    void n() {x ++; y++;}
}

class B extends  S{
    protected int y;

    void cumva() {y ++;}
}

class C extends S {

    void n() {x++;}
}