package victor.training.refactoring;

public class AB {
    final A a;
    final B b;

    public AB(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public void fa() {
        a.f();
    }

    public void fab() {
        a.f();
        b.f();
    }
}