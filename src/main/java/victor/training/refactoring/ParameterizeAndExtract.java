package victor.training.refactoring;

class ParameterizeAndExtract {

    public static void again(int n, int i) {
        for (int j = 0; j < i; j++) {
            if (n + j < 0) {
                System.out.println("Code " + j);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void f(int n) {
        System.out.println("Logic F");
        again(n, 4);
    }


}
class AnotherClass {

    public void g(int n) {
        System.out.println("Logic G");
        ParameterizeAndExtract.again(n, 3);
    }

}