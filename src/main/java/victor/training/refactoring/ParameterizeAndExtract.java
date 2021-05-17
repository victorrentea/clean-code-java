package victor.training.refactoring;

class ParameterizeAndExtract {

    public static  void g(int n) {
        System.out.println("Logic G");

        common(n, 3);
    }

    public void f(int n) {
        System.out.println("Logic F");

        common(n, 4);
    }

    private static void common(int n, int i2) {
        for (int i = 0; i < i2; i++) {
            if (n + i < 0) {
                System.out.println("Code " + i);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


}
class AnotherClass {

}