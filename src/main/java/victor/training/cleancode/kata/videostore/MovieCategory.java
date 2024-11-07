package victor.training.cleancode.kata.videostore;

public enum MovieCategory {
    CHILDRENS(2),
    REGULAR(0),
    NEW_RELEASE(1);

    final int priceCode;

    MovieCategory(int priceCode) {
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }
}