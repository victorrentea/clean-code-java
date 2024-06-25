package victor.training.cleancode.kata.videostore;


import lombok.NonNull;

public record Movie(String title, @NonNull PriceCode priceCode) {

    public enum PriceCode {
        CHILDREN,
        REGULAR,
        NEW_RELEASE,
        ELDERS
    }
}