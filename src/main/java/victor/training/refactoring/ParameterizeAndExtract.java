package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logic F");
        doLoopSmartName(4);
    }

    public void g() {
        System.out.println("Logic G");
        doLoopSmartName(3);
    }

    private void doLoopSmartName(int i2) {
        for (int i = 0; i < i2; i++) {
            System.out.println("Code " + i);
        }
    }

}