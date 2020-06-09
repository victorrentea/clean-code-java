package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logic F");
        for (int i = 0; i < 4; i++) {
            System.out.println("Code " + i);
        }
    }
    public void g() {
        System.out.println("Logic G");
        for (int i = 0; i < 3; i++) {
            String x = "Code" +
                    " " + i;
            System.out.println(x);
        }
    }

}