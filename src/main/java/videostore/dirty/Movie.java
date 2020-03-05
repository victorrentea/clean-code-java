package videostore.dirty;

import lombok.NonNull;

//class ChildrenMovie extends Movie {
//}
//class RegularMovie extends Movie {
//}
//class NewReleaseMovie extends Movie {
//	@Override
//	public double computePrice(int daysRented) {
//		return daysRented * 3;
//	}
//}


public abstract class Movie {

	public enum Category {
		CHILDREN,
		REGULAR,
		NEW_RELEASE
	}

	private final String title;

	private final Category category;

	public Movie(@NonNull String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

//	public abstract double computePrice(int daysRented);
}