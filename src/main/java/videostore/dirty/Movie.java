package videostore.dirty;

import static org.apache.commons.lang.StringUtils.isBlank;

public class Movie {
	
	public enum Category {
		CHILDRENS /*
					 * {
					 * 
					 * @Override public double computePrice(int days) { // TODO Auto-generated
					 * method stub return 0; } }
					 */,
		REGULAR,
		NEW_RELEASE,
		VIEJAS /*
				 * {
				 * 
				 * @Override public double computePrice(int days) { // TODO Auto-generated
				 * method stub return 0; } }
				 */;
		
//		abstract public double computePrice(int days); 
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		if (isBlank(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public boolean isNewRelease() {
		return getCategory() == Movie.Category.NEW_RELEASE;
	}
}