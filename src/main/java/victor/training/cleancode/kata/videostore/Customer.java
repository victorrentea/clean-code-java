package victor.training.cleancode.kata.videostore;

class Customer {
  private final String name;
  private final StatementView rentals = new StatementView();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie movie, int daysRented) {
    rentals.addRental(new Rental(movie, daysRented));
  }

  public String getName() {
    return name;
  }

  public String generateStatement() {
    return "Rental Record for " + name + "\n" + rentals.generateStatementBody();
  }



}