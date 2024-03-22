package victor.training.cleancode.kata.videostore;

record Rental(Movie movie, int daysRented) {
  public double calculateAmount() {
      double amount = 0;
      final int daysRented = daysRented();
      switch (movie().category()) {
      case REGULAR:
          amount = 2;
          if (daysRented > 2) {
              amount += (daysRented - 2) * 1.5;
          }
          break;
      case NEW_RELEASE:
          amount = daysRented * 3;
          break;
      case CHILDREN:
          amount = 1.5;
          if (daysRented > 3) {
              amount += (daysRented - 3) * 1.5;
          }
          break;
      }
      return amount;
  }
}
