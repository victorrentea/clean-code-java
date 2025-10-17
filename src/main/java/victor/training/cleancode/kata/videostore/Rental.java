package victor.training.cleancode.kata.videostore;

public record Rental (Movie movie, int daysRented) {

    int calculateRewardPoints() {
        return movie.category() == MovieCategory.NEW_RELEASE && daysRented > 1 ? 2 : 1;
    }

  String generateStatementLine() {
    return "\t" + movie().title() + "\t" + movie().getCost(daysRented) + "\n";
  }
}
