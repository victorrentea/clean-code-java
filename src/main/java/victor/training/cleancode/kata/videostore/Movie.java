package victor.training.cleancode.kata.videostore;

public record Movie(String title, MovieType movieType) {
    public static final double REGULAR_BASE_COST = 2.0;
    public static final double REGULAR_EXTRA_COST = 1.5;
    public static final double NEW_RELEASE_COST = 3.0;
    public static final double CHILDREN_BASE_COST = 1.5;
    public static final double CHILDREN_EXTRA_COST = 1.5;
    public static final int REGULAR_DAYS_THRESHOLD = 2;
    public static final int CHILDREN_DAYS_THRESHOLD = 3;
    public static final int BASE_RENTER_POINTS = 1;

    public double calcRentalCost(int daysRented) {
        return switch (movieType) {
            case REGULAR -> REGULAR_BASE_COST + Math.max(0, (daysRented - REGULAR_DAYS_THRESHOLD) * REGULAR_EXTRA_COST);
            case NEW_RELEASE -> daysRented * NEW_RELEASE_COST;
            case CHILDREN ->
                    CHILDREN_BASE_COST + Math.max(0, (daysRented - CHILDREN_DAYS_THRESHOLD) * CHILDREN_EXTRA_COST);
        };
    }

    public int calcRenterPoints(int daysRented) {
        return isQualifiedForBonus(daysRented) ? BASE_RENTER_POINTS + 1 : BASE_RENTER_POINTS;
    }

    private boolean isQualifiedForBonus(int daysRented) {
        return movieType == MovieType.NEW_RELEASE && daysRented > BASE_RENTER_POINTS;
    }
}