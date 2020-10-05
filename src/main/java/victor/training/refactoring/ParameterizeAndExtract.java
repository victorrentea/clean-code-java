package victor.training.refactoring;

class ParameterizeAndExtract {

    // existing
    public void f() {
        System.out.println("Logic F");
        for (int i = 0; i < 4; i++) {
            System.out.println("Code " + i * (i - 1));
        }
    }

}
class MyStory {
    public void g() {
        System.out.println("Logic G");
        for (int i = 0; i < 3; i++) {
            int temp = i * (i - 1);
            String x = "Code " + temp;
            System.out.println(x);
        }
    }

}