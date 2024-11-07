package victor.training.cleancode.kata.videostore;
public record Movie (String title, PriceCode priceCode) {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	enum PriceCode {
		REGULAR(0) {
			@Override
			public double calculateAmount(int daysRented) {
				double thisAmount = 2;
				if (daysRented > 2) {
					thisAmount += (daysRented - 2) * 1.5;
				}
				return thisAmount;
			}
		} ,
		NEW_RELEASE(1) {
			@Override
			public double calculateAmount(int daysRented) {
				return daysRented * 3;
			}
		},
		CHILDRENS(2) {
			@Override
			public double calculateAmount(int daysRented) {
				double thisAmount = 1.5;
				if (daysRented > 3) {
					thisAmount += (daysRented - 3) * 1.5;
				}
				return thisAmount;
			}
		};
		private final int code;
		PriceCode(int code) {
			this.code = code;
		}

		public abstract double calculateAmount(int daysRented);

		public int code() {
			return code;
		}
	}
}