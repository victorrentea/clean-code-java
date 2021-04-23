package victor.training.refactoring;

import java.util.function.Consumer;

class ParameterizeAndExtract {

    public void f(int n) {
        System.out.println("Logic F");
//        for (int i = 0; i < 4; i++) {
//            if (n + i < 0) {
//                System.out.println("Code " + i);
//                System.out.println("Code " + i);
//            } else {
//                throw new IllegalArgumentException();
//            }
//        }
        extracted(n, i1 -> {
            System.out.println("Code " + i1);
            System.out.println("Code " + i1);
        }, 4);
    }
    public void g(int n) {
        System.out.println("Logic G");
        extracted(n, i1 -> System.out.println("Code " + i1), 3);
    }

    private void extracted(int n, Consumer<Integer> consumer, int p) {
        for (int i = 0; i < p; i++) {
            if (n + i < 0) {
                consumer.accept(i);
            } else throw new IllegalArgumentException();
        }
    }

}
class AnotherClass {




}