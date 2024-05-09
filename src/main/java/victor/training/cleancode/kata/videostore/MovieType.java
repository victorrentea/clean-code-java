package victor.training.cleancode.kata.videostore;

public enum MovieType {
    CHILDREN(2),
    REGULAR (0),
    NEW_RELEASE (1);

    private final int i;

    MovieType(int i) {

        this.i = i;
    }
}
