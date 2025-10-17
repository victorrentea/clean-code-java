package victor.training.cleancode.kata.videostore;

import java.util.List;
import java.util.stream.Collectors;

public class Statement {
    private final String customerName;
    private final List<MoviePrice> moviePrices;
    private final long frequentRenterPoints;

    public Statement(String customerName, List<MoviePrice> moviePrices, long frequentRenterPoints) {
        this.customerName = customerName;
        this.moviePrices = moviePrices;
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public record MoviePrice(String title, double price) { }

    public String format() {
        String result = "Rental Record for " + customerName + "\n";
        result += moviePrices.stream()
                .map(moviePrice -> "\t" + moviePrice.title + "\t" + moviePrice.price + "\n")
                .collect(Collectors.joining());
        double totalAmount = moviePrices.stream().mapToDouble(MoviePrice::price).sum();
        result += "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
