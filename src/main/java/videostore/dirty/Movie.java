package videostore.dirty;

// applicationContext.getBean(
public class Movie {
	public enum Type {
		REGULAR(() -> 2), NEW_RELEASE(new  NewReleasePriceCalculator()), CHILDREN(() -> 1);

		public final Rental.PriceCalculator calculator;

		Type(Rental.PriceCalculator calculator) {

			this.calculator = calculator;
		}
	}

	private final String title;
	private final Type type;

	public Movie(String title, Type type) {
		this.title = title;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public Type getType() {
		return type;
	}
}
