package victor.training.cleancode;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");
        for (int i = 0; i < 4; i++) {
            if (n + i < 0) {
                System.out.println("Code " + i);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


}
class SomeOtherClass {

    public void g(int n) {
        System.out.println("Logic G");
        try {
            for (int j = 0; j < 3; j++) {
                if (n + j < 0) {
                    String x = "Code" +
                            " " + j;
                    System.out.println(x);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Rethrow", e);
        }
    }
}