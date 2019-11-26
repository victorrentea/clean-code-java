package videostore.dirty;

public enum MovieType {
	CHILDREN(2){
		@Override
		public double computePrice(int daysRented) {
			return 0;
		}
	},
	REGULAR(0) {
		@Override
		public double computePrice(int daysRented) {
			double price = 2;
			if (daysRented > 2)
				price += (daysRented - 2) * 1.5;
			return price;
		}
	},
//	BABACIUNI(5),
	NEW_RELEASE(1) {
	@Override
	public double computePrice(int daysRented) {
		return 0;
	}
};

	private final int id;

	MovieType(int id) {
		this.id = id;
	}

	public abstract double computePrice(int daysRented);
}
