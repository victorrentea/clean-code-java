package victor.training.cleancode.kata.videostore;
public record Movie( String title, PriceCode priceCode) {

    public PriceCode getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}