package victor.training.cleancode.kata.videostore;


public class Movie {

	Category category;

	private String title;


	public Movie(String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Category getPriceCode() {
		return category;
	}



	public String getTitle() {
		return title;
	};


}
