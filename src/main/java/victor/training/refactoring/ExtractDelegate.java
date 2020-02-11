package victor.training.refactoring;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExtractDelegate {
    private final A a;
    private final B b;
    private final C c;

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

