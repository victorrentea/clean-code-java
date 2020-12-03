package victor.training.refactoring;

public class ExtractDelegate {
    public static void main(String[] args) {
        BigOne one = new BigOne();
        one.getAbInteractor().fab();
        one.fcab();
        one.fc();
    }
}
class BigOne {
    private final victor.training.refactoring.ABInteractor abInteractor = new ABInteractor();
    private C c = new C();

    public ABInteractor getAbInteractor() {
        return abInteractor;
    }

    public void fcab() {
        c.f();
        abInteractor.fab();
    }
    public void fc() {
        abInteractor.fa();
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

