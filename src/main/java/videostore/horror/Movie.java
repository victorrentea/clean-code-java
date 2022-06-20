package videostore.horror;

import java.util.Objects;

public class Movie {
    enum PriceCode {
        CHILDREN,
        REGULAR,
        NEW_RELEASE,
        ELDER
    }

    private final String title;
    private final PriceCode priceCode;

    public Movie(String title, PriceCode priceCode) {
        this.title = Objects.requireNonNull(title);
        this.priceCode = Objects.requireNonNull(priceCode);
    }

    public PriceCode getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

}