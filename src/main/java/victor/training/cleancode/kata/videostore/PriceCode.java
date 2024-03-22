package victor.training.cleancode.kata.videostore;

/**
 * MovieCategory
 * <br>
 * <code>victor.training.cleancode.kata.videostore.MovieCategory</code>
 * <br>
 *
 * @since 22 March 2024
 */
public enum PriceCode {
    CHILDRENS(2),
    REGULAR(0),
    NEW_RELEASE(1);

    private final int code;

    PriceCode(int code) {
        this.code = code;
    }


}
