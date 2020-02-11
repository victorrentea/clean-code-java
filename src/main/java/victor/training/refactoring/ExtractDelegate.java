package victor.training.refactoring;

public class ExtractDelegate {
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

    public void a() {
        a.f();
    }
    public void ab() {
        a.f();
        b.f();
    }
    public void cab() {
        c.f();
        a.f();
        b.f();
    }
    public void c() {
        c.f();
    }
}

interface A {
    void f();
}
interface B {
    void f();
}
interface C {
    void f();
}

