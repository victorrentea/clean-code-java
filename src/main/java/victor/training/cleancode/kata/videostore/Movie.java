package victor.training.cleancode.kata.videostore;

public record Movie(String title, PriceCode priceCode) {
	public enum PriceCode {
		REGULAR,
		NEW_RELEASE,
		CHILDRENS
	}
}



