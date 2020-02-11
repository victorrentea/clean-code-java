package victor.training.refactoring.inheritance;


class S {
    protected int z;
}
class A extends S {
    protected int x;
    private int y;
    void n() {
        m();
        System.out.println(z);
        System.out.println(x);
    }

    protected void m() {System.out.println(x);}
}

class B extends S {
    private int y;
    void p() { System.out.println(z); }

}
class C extends S {
    void n() { System.out.println(z); }
}