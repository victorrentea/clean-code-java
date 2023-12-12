package victor.training.cleancode.kata.videostore.horror;

public enum Category {
    REGULAR(0),
    NEW_RELEASE(1),
    CHILDRENS(2);
    private int priceCode;
    Category(int priceCode) { this.priceCode = priceCode; }
    public int getPriceCode() { return priceCode; }
}
