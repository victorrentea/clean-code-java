package videostore.dirty;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;

import static java.util.Objects.requireNonNull;


public class Movie {
    enum Category {
        CHILDRENS(2),
        REGULAR(0),
        NEW_RELEASE(1),
        ;
        public final int key;
        Category(int key) {
            this.key = key;
        }
    }

    private final String title;
    private final Category priceCode;

    public Movie(String title, Category priceCode) {
        this.title = requireNonNull(title);
        this.priceCode = requireNonNull(priceCode);
    }

    public Category getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }
}