package videostore.horror;

public record Rental(Movie movie, int daysRented) {
    public double computePrice() {
        return movie().category().computePrice(daysRented());
    }
}
