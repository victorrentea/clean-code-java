package victor.training.cleancode.kata.videostore.horror;
public class Movie {

	private Category category;
	private String title;


	public Movie(String title, Category category) {
		this.category = category;
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	};
}