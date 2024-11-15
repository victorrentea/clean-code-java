package victor.training.cleancode.kata.videostore;

public enum MovieType {
     CHILDREN(2), REGULAR(0), NEW_RELEASE(1);

     public final int priceCode;

    MovieType(int priceCode) {
        this.priceCode = priceCode;
    }
}
