package victor.training.cleancode.refactoring;

public class ExtractDelegate {
    public static void main(String[] args) {
        BigOne one = new BigOne();
        one.fa();
        one.fab();
        one.fcab();
        one.fc();
    }
}
class BigOne {
    private A a = new A();
    private B b = new B();
    private C c = new C();

    public void fa() {
        a.f();
    }
    public void fab() {
        a.f();
        b.f();
    }
    public void fcab() {
        c.f();
        a.f();
        b.f();
    }
    public void fc() {
        c.f();
    }
}

class A {
    void f(){System.out.println("A.placeOrder");}
}
class B {
    void f(){System.out.println("B.placeOrder");}
}
class C {
    void f(){System.out.println("C.placeOrder");}
}

