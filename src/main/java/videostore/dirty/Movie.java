package videostore.dirty;

public class Movie {
	public enum Category {
		CHILDREN{
			@Override
			public double computePrice() {
				return 0;
			}
		},
		REGULAR {
			@Override
			public double computePrice() {
				return 0;
			}
		},
		NEW_RELEASE {
			@Override
			public double computePrice() {
				return 0;
			}
		};

		public abstract double computePrice();

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