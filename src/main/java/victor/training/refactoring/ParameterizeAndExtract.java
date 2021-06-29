package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");
        extracted(n, 4);
    }



    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    // 400 lines of code
    public void g(int n) {
        System.out.println("Logic G");
        try {

            extracted(n, 3);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void extracted(int n, int i) {
        for (int j = 0; j < i; j++) {
            if (n + j < 0) {
                System.out.println("Code " + j);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
class AnotherClass {

}