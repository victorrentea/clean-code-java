package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int daysRented) {
    public static final double REGULAR_BASE_COST = 2.0;
    public static final double REGULAR_EXTRA_COST = 1.5;
    public static final double NEW_RELEASE_COST = 3.0;
    public static final double CHILDREN_BASE_COST = 1.5;
    public static final double CHILDREN_EXTRA_COST = 1.5;
    public static final int REGULAR_DAYS_THRESHOLD = 2;
    public static final int CHILDREN_DAYS_THRESHOLD = 3;


    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code
    // more code

    public double calculateCost(int daysRented) {
        return switch (movie.movieType()) {
            case REGULAR -> REGULAR_BASE_COST + Math.max(0, (daysRented - REGULAR_DAYS_THRESHOLD) * REGULAR_EXTRA_COST);
            case NEW_RELEASE -> daysRented * NEW_RELEASE_COST;
            case CHILDREN ->
                CHILDREN_BASE_COST + Math.max(0, (daysRented - CHILDREN_DAYS_THRESHOLD) * CHILDREN_EXTRA_COST);
//            default -> 0;

            // NEVER
//            default -> throw new IllegalArgumentException("Unknown movie type: " + movie.movieType());

            // DO NOT DEFINE A DEFAULT CASE.
            // COMPILATION FAILS WHEN A NEW MOVIE TYPE IS ADDED
        };
    }
}