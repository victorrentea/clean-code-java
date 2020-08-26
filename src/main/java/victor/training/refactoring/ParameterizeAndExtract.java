package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logic F");
        howTheHeck(4);
    }

    private void howTheHeck(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Code " + i);
        }
    }

    public void g() {
        System.out.println("Logic G");
        howTheHeck(3);
    }

}