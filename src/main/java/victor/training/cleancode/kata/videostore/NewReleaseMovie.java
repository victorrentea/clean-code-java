package victor.training.cleancode.kata.videostore;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double computePrice(int daysRented) {
        double amount = 0;
        amount += daysRented * 3;
        return amount;
    }
}
