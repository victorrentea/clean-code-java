package victor.training.cleancode;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");
        extracted(n, 4, "CodeY ");
    }

    public void g(int n) {
        System.out.println("Logic G");
        try {
            extracted(n, 3, "CodeX ");
        } catch (Exception e) {
            throw new RuntimeException("Rethrow", e);
        }
    }

    private void extracted(int n, int m, String s) {
        for (int j = 0; j < m; j++) {
            if (n + j < 0) {
                System.out.println(s + j);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

}
class SomeOtherClass {

}