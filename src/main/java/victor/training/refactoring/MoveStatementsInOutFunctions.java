package victor.training.refactoring;

public class MoveStatementsInOutFunctions {

    public static void f() {
        System.out.println("Line1f");
        System.out.println("Line2"); // idem
        m2();
        System.out.println("Line6f");
    }

    public static void g() {
        System.out.println("Line1g");
        System.out.println("Line2"); // idem
        m2();
        System.out.println("Line6g");
    }

    public static void m2() {
        System.out.println("Line3");
        System.out.println("Line4");
        System.out.println("Line5"); // split -> f and g
    }

    public static void main(String[] args) {
        f();
        System.out.println("---");
        g();
    }
}
