package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Type {
		CHILDREN{
			@Override
			public double computePrice(int daysRented) {
				double price = 1.5;
				if (daysRented > 3)
					price += (daysRented - 3) * 1.5;
				return price;
			}
		},
		REGULAR {
			@Override
			public double computePrice(int daysRented) {
				double price = 2;
				if (daysRented > 2) {
					price += (daysRented - 2) * 1.5;
				}
				return price;
			}
		},
		NEW_RELEASE {
			@Override
			public double computePrice(int daysRented) {
				return daysRented * 3;
			}
		};
		public abstract double computePrice(int daysRented);

	}
	private final String title;
	private final Type type;

	public Movie(String title, Type type) {
		this.title = title;
		this.type = requireNonNull(type);
		// requires a db migration: UPDATE MOVIES SET TYPE='CHILDREN' WHERE TYPE IS NULL;
		// + ALTER TABLE MOVIES SET TYPE NOT NULL;

		// daca-s ff multe --> PROCEDURA temporara care migreaza datele. dupa care o stergi.
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	};
}