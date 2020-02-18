package victor.training.refactoring;

public class MoveStatementsInOutFunctions {

    public static void f() {
        System.out.println("Line1f");
        m4();
        System.out.println("Line5f"); // split
        System.out.println("Line6f");
    }

    public static void g() {
        System.out.println("Line1g");
        m4();
        System.out.println("Line5g"); // split
        System.out.println("Line6g");
    }

    private static void m4() {
        System.out.println("Line2"); // idem
        System.out.println("Line3");
        System.out.println("Line4");
    }

    public static void main(String[] args) {
        f();
        System.out.println("---");
        g();
    }
}
