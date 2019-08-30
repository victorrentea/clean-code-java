package videostore.dirty;
public class Movie {

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

		public abstract double computePrice(int daysRented);
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	};
}