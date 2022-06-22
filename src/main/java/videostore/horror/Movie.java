package videostore.horror;

import java.time.LocalDate;
import java.util.Objects;


//class RegularMovie extends Movie {}
//class NewReleaseMovie extends Movie {}
public  class Movie {
//    boolean newRelease;
    enum PriceCode {
        CHILDREN, //(Rental::computeRegularPrice),
        REGULAR,
        NEW_RELEASE,
        ELDER

    }

    private LocalDate releaseDate; // is not null only new Release

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