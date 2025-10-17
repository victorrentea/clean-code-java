package victor.training.cleancode.kata.videostore;

import java.util.Objects;

public record Movie(String title, MoviePricingCategory pricingCategory) {
    public Movie {
        Objects.requireNonNull(title);
        Objects.requireNonNull(pricingCategory);
    }
}