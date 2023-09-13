package videostore.horror;

import java.util.Objects;

public record Movie(String title, PriceCode priceCode) {
    public Movie(String title, PriceCode priceCode) {
        this.title = title;
        this.priceCode = Objects.requireNonNull(priceCode);
    }

}