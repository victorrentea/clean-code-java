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

    private void commonStuffWithExtraParam(int i2) {
        for (int i = 0; i < i2; i++) {
            System.out.println("Cod " + i);
        }
    }

}