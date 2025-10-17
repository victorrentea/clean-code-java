package victor.training.cleancode.kata.videostore;

public class Movie {
    private final String _title;
    private PriceCode _priceCode;

    public Movie(String title, PriceCode priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public PriceCode getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(PriceCode arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }

    ;
}