package videostore.dirty;

public class Movie {

	enum Type {
		REGULAR{
            @Override
            public double computePrice() {
                return 0; //logica de Regular
            }
        },
		NEW_RELEASE,
		CHILDREN,
        BABCIUNI {
            @Override
            public double computePrice() {
                return 0;
            }
        };

        public abstract double computePrice();
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