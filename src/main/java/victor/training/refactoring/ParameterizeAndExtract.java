package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logic F");

        extracted(4);
    }
    public void g() {
        System.out.println("Logic G");


        extracted(3);
    }

    private void extracted(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Code 10lines " + i);
        }
    }

}