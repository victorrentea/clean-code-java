package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logica f");
        for (int i = 0; i < 4; i++) {
            System.out.println("Cod " + i);
        }
    }

    public void g() {
        System.out.println("Logica g");
        for (int i = 0; i < 3; i++) {
            System.out.println("Cod " + i);
        }
    }

}