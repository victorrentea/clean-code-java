package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int days) {
  public MovieType movieType() {
    return movie.type();
  }

  double price() {
    return switch (movie.type()) {// java 17
      case REGULAR -> f1();
      case NEW_RELEASE -> days * 3;
      case CHILDREN -> f2();
      // TODO incercati sa adaugati HORROR si vedeti daca stergeti acest default ce se intampla
      default -> throw new IllegalArgumentException("Movie type not found!");
    };
  }

  // TODO rename and extract
  private double f2() {
    double result;
    result = 1.5;
    if (days > 3)
      result += (days - 3) * 1.5;
    return result;
  }

  private double f1() {
    double result;
    result = 2;
    if (days > 2)
      result += (days - 2) * 1.5;
    return result;
  }
}
