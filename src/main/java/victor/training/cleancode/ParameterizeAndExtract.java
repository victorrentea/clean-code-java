package victor.training.cleancode;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");
        int m = 4;
        common(n, m);
    }
    public void g(int n) {
        System.out.println("Logic G");
        try {
            common(n, 3);
        } catch (Exception e) {
            throw new RuntimeException("Rethrow", e);
        }
    }

    private void common(int n, int m) {
        for (int i = 0; i < m; i++) {
            if (n + i < 0) {
                System.out.println("Code " + i);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
class SomeOtherClass {

}