package videostore.dirty;
public abstract class Movie {

	enum Category {
		CHILDREN{
			public double computePrice(int daysRented) {
				double price = 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				return price;
			}
		},
		REGULAR{
			public double computePrice(int daysRented) {
				double price = 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				return price;
			}
		},
		NEW_RELEASE{
			public double computePrice(int daysRented) {
				return daysRented * 3;
			}
		},
		ELDERS {
			public double computePrice(int daysRented) {
				return 1;// tre sa pun ceva ca altfel nu compileaza
			}
		};

	}

	private final String title;

	public Movie(String title) {
		this.title = title;
	}

	public abstract double computePrice(int daysRented);
	public abstract int computeFreqRenterPoints(int daysRented);
	public abstract int computeMaxRentalDays(int daysRented);

	public String getTitle() {
		return title;
	};
}

class NewReleaseMovie extends Movie {
	public NewReleaseMovie(String title) {
		super(title);
	}
	public double computePrice(int daysRented) {
		return daysRented * 3;
	}
	public int computeFreqRenterPoints(int daysRented) {
		return 0;
	}
	public int computeMaxRentalDays(int daysRented) {
		return 0;
	}
}

