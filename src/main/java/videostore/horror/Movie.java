package videostore.horror;

public record Movie(String title, PriceCode priceCode) {

    enum PriceCode {
        CHILDREN,
        REGULAR,
        NEW_RELEASE;

    }

}