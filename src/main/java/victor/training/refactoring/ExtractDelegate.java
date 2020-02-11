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
    private final A a;
    private final B b;
    private final C c;

    public BigOne(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public C getC() {
        return c;
    }

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
    void f(){};
}
class B {
    void f(){};
}
class C {
    void f(){};
}

