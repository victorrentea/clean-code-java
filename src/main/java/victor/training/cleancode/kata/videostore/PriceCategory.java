package victor.training.cleancode.kata.videostore;

public enum PriceCategory {
    REGULAR(0), NEW_RELEASE(1), CHILDRENS(2);

    private int mIndex;
    PriceCategory(int pIndex) {
        this.mIndex = pIndex;
    }

    public int getIndex() {
        return mIndex;
    }
}
