package victor.training.cleancode.kata.videostore;
public class Movie {
//	public static final int CHILDRENS = 2;
//	public static final int REGULAR = 0;
//	public static final int NEW_RELEASE = 1;
	private String _title;
	Category category;

	public Movie(String title, Category category) {
		_title = title;
		this.category = category;
	}


	public Integer getPriceCode() {
		return category.getCode();
	}

	public String getTitle() {
		return _title;
	};
}