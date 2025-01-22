package victor.training.cleancode.java;


public class Switch {

    // run tests
    public int computePrice(Movie movie, int days) {// query method
        switch (movie.category()) {
            case REGULAR:
                return days + 1;
            case NEW_RELEASE:
                return days * 2;
            case CHILDREN:
                return 5;
        }
        return 0;
    }

    // run tests
    public void processMovie(Movie movie) {
        System.out.println("Some repo calls");
        System.out.println("Some shared initial stuff");

        // check parental advisory
        switch (movie.category()) {
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

    record Movie(Category category, String title) {
        @Override
        public String toString() {
            return "Movie{category=" + category + ", title='" + title + "'}";
        }

        enum Category {
            REGULAR, NEW_RELEASE, CHILDREN
        }
    }
}
