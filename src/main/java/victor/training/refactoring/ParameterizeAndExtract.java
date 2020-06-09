package victor.training.refactoring;

class ParameterizeAndExtract {

    // linia 200
    public void f() {
        System.out.println("Logica f");
        commonStuffWithExtraParam(4);
    }

    //linia 500
    public void g() {
        System.out.println("Logica g");
        commonStuffWithExtraParam(3);
    }

    private void commonStuffWithExtraParam(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Cod " + i);
        }
    }

}