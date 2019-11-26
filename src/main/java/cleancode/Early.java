package cleancode;

public class Early {
    public static void main(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        } else {
            System.out.println(args);
        }

    }
}
