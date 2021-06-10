package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");

        aaa(n, 4, "Z", "Code ");
    }

    public void g(int n) {
        System.out.println("Logic G");


        aaa(n, 3, "Y", "Code ");
    }

    private void aaa(int n, int i2, String y, String code) {
        for (int i = 0; i < i2; i++) {
            if (n + i < 0) {
                System.out.println(code + i + y);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
class AnotherClass {



}