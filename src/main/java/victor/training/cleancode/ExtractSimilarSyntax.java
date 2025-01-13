package victor.training.cleancode;

class ParameterizeExtractSimilarCode {

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

    public void g(int n) {
        System.out.println("Logic G");
        try {
            for (int i = 0; i < 4; i++) {
                if (n + i < 0) {
                    System.out.println("Code " + i);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Rethrow", e);
        }
    }

}