package victor.training.refactoring;

public class ExtractDelegate {
    public static void main(String[] args) {
        BigOne one = new BigOne(new A(), new B(), new C());
        one.fa();
        one.getB();
        one.fab();
        one.getC();
    }
}
class BigOne {
    private final C c;
    private final victor.training.refactoring.AB ab;

    public BigOne(A a, B b, C c) {
        this.ab = new AB(a, b);
        this.c = c;
    }

    public A getA() {
        return ab.getA();
    }

    public B getB() {
        return ab.getB();
    }

    public C getC() {
        return c;
    }

    public void fa() {
        ab.fa();
    }
    public void fab() {
        ab.fab();
    }
    public void fcab() {
        c.f();
        ab.getA().f();
        ab.getB().f();
    }
    public void fc() {
        c.f();
    }
}

class A {
    void f(){};
}
class B {
    void f(){};
}
class C {
    void f(){};
}

