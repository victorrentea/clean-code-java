package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");

        for (int i = 0; i < 4; i++) {
            if (n + i < 0) {
                System.out.println("CodeY " + i);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void g(int n) {
        System.out.println("Logic G");

        for (int i = 0; i < 3; i++) {
            if (n + i < 0) {
                System.out.println("CodeX " + i);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
class AnotherClass {

}