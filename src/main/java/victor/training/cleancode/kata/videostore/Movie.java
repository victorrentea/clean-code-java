package victor.training.cleancode.kata.videostore;

public record Movie(String title, MovieType movieType) {

    public static final int BASE_RENTER_POINTS = 1;

    public int calcRenterPoints(int daysRented) {
        return isQualifiedForBonus(daysRented) ? BASE_RENTER_POINTS + 1 : BASE_RENTER_POINTS;
    }

    private boolean isQualifiedForBonus(int daysRented) {
        return movieType == MovieType.NEW_RELEASE && daysRented > BASE_RENTER_POINTS;
    }
}