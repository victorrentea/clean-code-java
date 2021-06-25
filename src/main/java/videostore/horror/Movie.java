package videostore.horror;

public class Movie {

	private final String title;
	private final MovieCategory category;

	public Movie(String title, MovieCategory category) {
		this.title = title;
		if (category == null) {
			throw new IllegalArgumentException();
		}
		this.category =  category;
	}
//public abstract double determinePrice();
	public MovieCategory getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}
}

//class RegularMovie extends Movie {
//
//	public RegularMovie(String title, MovieCategory category) {
//		super(title, category);
//	}
//
//	@Override
//	public double determinePrice() {
//		return 0;
//	}
//}