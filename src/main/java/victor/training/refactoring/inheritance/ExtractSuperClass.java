package victor.training.refactoring.inheritance;


class S {
    protected int x;
    void m() {System.out.println(x);}
}
class A{
    private S s;
    private int y;

    void m() {System.out.println(s.x);}
//    void n() {x ++;}
}

class B {
    private int y;
}
class C  {
    private S s;
    void n() {s.x++;}
}



interface I1 {
    default int f() {return 1;}
}
interface I2 {
    default int f() {return 1;}
}
class A2 implements I1,I2 {

    @Override
    public int f() {
        return I1.super.f();
    }
}