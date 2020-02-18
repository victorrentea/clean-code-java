package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logica f");
        aaa(4);
    }

    private void aaa(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Cod " + i);
        }
    }

    public void g() {
        System.out.println("Logica g");
        aaa(3);
    }

}