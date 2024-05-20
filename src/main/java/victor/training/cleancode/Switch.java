package victor.training.cleancode;


class Movie {
    enum Category {
        REGULAR, NEW_RELEASE, CHILDREN
    }

    private final Category category;
    private final String title;

    public Movie(Category category, String title) {
        this.category = category;
        this.title = title;
    }
    public Category getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Movie{category=" + category + ", title='" + title + "'}";
    }
}


public class Switch {

    // run tests
    public int computePrice(Movie movie, int days) {// query method
        switch (movie.getCategory()) {
            case REGULAR:
                return days + 1;
            case NEW_RELEASE:
                return days * 2;
            case CHILDREN:
                return 5;
        }
        return 0; // ?!.. Free!! Deducted from your salary!
    }

    // run tests
    public void processMovie(Movie movie) {
        System.out.println("Some repo calls");
        System.out.println("Some shared initial stuff");

        // check parental advisory
        switch (movie.getCategory()) {
            case REGULAR:
                System.out.println("Process regular movie: " + movie);
                break;
            case CHILDREN:
                System.out.println("Process children movie: " + movie);
                break;
            case NEW_RELEASE:
                System.out.println("Process new release movie: " + movie);
                break;
        }

        System.out.println("More common code after");
    }
}
