package victor.training.refactoring;

class ParameterizeAndExtract {

    public void f() {
        System.out.println("Logic F");

        common(4);
    }

    public void g() {
        System.out.println("Logic G");

        common(3);
    }

    private void common(int i) {
        for (int j = 0; j < i; j++) {
            System.out.println("CodeYYY " + j);
        }
    }

}
class X  {

}