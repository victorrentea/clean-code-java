package victor.training.cleancode.kata.videostore;
public enum PriceType {
    REGULAR(0),
    NEW_RELEASE(1),
    CHILDRENS(2);

    final int categoryCode;

    PriceType(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getCategoryCode() { return categoryCode; }
}
