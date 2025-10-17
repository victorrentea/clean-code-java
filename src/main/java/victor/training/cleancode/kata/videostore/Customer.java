package victor.training.cleancode.kata.videostore;

class Customer {
  private final String name;
  private final Rentals rentals;

  public Customer(String name) {
    rentals = new Rentals();
    this.name = name;
  }

  public void addRental(Movie movie, int daysRented) {
    rentals.addRental(new Rental(movie, daysRented));
  }

  public String getName() {
    return name;
  }

  public String generateStatement() {
    return "Rental Record for " + getName() + "\n" + rentals.generateStatementBody();
  }



}