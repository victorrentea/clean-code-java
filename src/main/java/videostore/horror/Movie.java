package videostore.horror;

//public abstract class Movie { abstract computePrice(days);
//class RegularMovie extends Movie { computePrice(..) {...}
public final class Movie {
    private final String title;
    private final MovieCategory category;

    public Movie(String title, MovieCategory category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public MovieCategory getCategory() {
        return category;
    }
}
