package victor.training.refactoring;

class ParameterizeAndExtract {

    // existing
    public void f() {
        System.out.println("Logic F");
        common(4);
    }

    public void g() {
        System.out.println("Logic G");
        common(3);
    }

    private void common(int n) {
        for (int i = 0; i < n; i++) {
            int temp = i * (i - 1);
            System.out.println("Code " + temp);
        }
    }

}