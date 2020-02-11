package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logica f");
        m(4);
    }

    public void g() {
        System.out.println("Logica g");
        m(3);
    }

    private void m(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Cod " + i);
        }
    }

}