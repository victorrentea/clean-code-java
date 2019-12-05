package videostore.dirty;

public class Movie {

	enum Type {
		REGULAR(Rental.NewReleaseMovieFunctions.class),
		NEW_RELEASE(),
		CHILDREN();
		public final Class<? extends Rental.MovieTypeFunctions> strategyClass;

        Type(Class<? extends Rental.MovieTypeFunctions> strategyClass) {
            this.strategyClass = strategyClass;
        }
    }

	private final String title;

    private final Type type;

    public Movie(String title, Type type) {
        this.title = title;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

}