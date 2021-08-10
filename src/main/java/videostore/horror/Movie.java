package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category  {
   	CHILDRENS {
			public double computePrice(int daysRented) {
				double price = 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				return price;
			}
		},
   	REGULAR {
			public double computePrice(int daysRented) {
				double price = 2;
				if (daysRented > 2)
					price += (daysRented - 2) * 1.5;
				return price;
			}
		},
   	NEW_RELEASE {
			public double computePrice(int daysRented) {
				return daysRented * 3;
			}
		};

   	public abstract double computePrice(int daysRented);
	}

   private final String title;
	private final Category category; // NOT NULL

   public Movie(String title, Category category) {
      this.title = title;
		this.category = requireNonNull(category);
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
      return title;
   }
}