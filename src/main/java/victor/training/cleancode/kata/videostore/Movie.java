package victor.training.cleancode.kata.videostore;

public record Movie(String title, PriceCode priceCode) {

	public enum PriceCode {
		CHILDREN,
		REGULAR,
		NEW_RELEASE
	}
}