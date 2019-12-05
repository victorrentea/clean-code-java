package videostore.dirty;

public class Movie {

	enum Type {
		REGULAR(new Rental.NewReleaseMovieFunctions()),
		NEW_RELEASE(functions),
		CHILDREN(functions);
		public final Rental.MovieTypeFunctions functions;

        Type(Rental.MovieTypeFunctions functions) {
            this.functions = functions;
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