package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category {
		CHILDREN /*{
			public double computePrice(int daysRented) {
				double price;
				price = 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				return price;
			}
		}*/,
		REGULAR,
		NEW_RELEASE,
////		ELDERS breaks compilation

		}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		this.title = requireNonNull(title);
		this.category = requireNonNull(category);
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}
}