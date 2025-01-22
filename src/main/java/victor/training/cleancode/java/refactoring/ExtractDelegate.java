package victor.training.cleancode.java.refactoring;

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
    private final A a = new A();
    private final B b = new B();
    private final C c = new C();

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
    void f(){System.out.println("A.f");}
}
class B {
    void f(){System.out.println("B.f");}
}
class C {
    void f(){System.out.println("C.f");}
}

