package victor.training.cleancode.kata.videostore;
public record Movie( String title, PriceCode priceCode) {

  public enum PriceCode {CHILDRENS, REGULAR, NEW_RELEASE}

	public PriceCode getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}