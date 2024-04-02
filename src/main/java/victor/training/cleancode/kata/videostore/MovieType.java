package victor.training.cleancode.kata.videostore;

public enum MovieType {
    REGULAR(0), NEW_RELEASE(1), CHILDREN(2);
    private final int value;
    MovieType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
