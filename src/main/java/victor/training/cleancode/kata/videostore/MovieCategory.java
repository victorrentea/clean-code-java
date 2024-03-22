package victor.training.cleancode.kata.videostore;

public enum MovieCategory {
    REGULAR(0),
    NEW_RELEASE(1),
    CHILDRENS(2);

    final int categoryCode;

    MovieCategory(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
