package victor.training.cleancode.kata.videostore.horror;

public enum MovieCategory {

    REGULAR(0),
    NEW_RELEASE(1),

    CHILDREN(2);

    public int priceCode;

    MovieCategory(int priceCode){
        this.priceCode=priceCode;
    }

}
