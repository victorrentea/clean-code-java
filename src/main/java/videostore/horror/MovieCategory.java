package videostore.horror;

public enum MovieCategory {
	CHILDREN(2),
	REGULAR(0),
	NEW_RELEASE(1);


	private final int priceCode;

	MovieCategory(int priceCode) {

		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}
}
