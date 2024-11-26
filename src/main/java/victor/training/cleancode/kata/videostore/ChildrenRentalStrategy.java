package victor.training.cleancode.kata.videostore;

public class ChildrenRentalStrategy implements MovieRentalStrategy {
    @Override
    public double calculatePrice(int rentalDays) {
        double price = 1.5;
        if (rentalDays > 3) {
            price += (rentalDays - 3) * 1.5;
        }
        return price;
    }

    @Override
    public int calculateFrequentRenterPoints(int rentalDays) {
        return 1;
    }
}
