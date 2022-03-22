package videostore.horror;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

class Rental {
   private final Movie movie;
   private final int daysRented;

   Rental(Movie movie, int daysRented) {
      this.movie = movie;
      this.daysRented = daysRented;
   }

   public Movie getMovie() {
      return movie;
   }

   public int getDaysRented() {
      return daysRented;
   }

   public int computeRenterPoints() {
      if (getMovie().isNewRelease() && getDaysRented() >= 2) {
			return 2;
		} else {
			return 1;
		}
   }

   // fun names lead you to expect the function is PURE "get
   public double getPrice() {
      switch (getMovie().getCategory()) {
         case REGULAR:
            return computeRegularPrice();
         case NEW_RELEASE:
            return computeNewReleasePrice();
         case CHILDREN:
            return computeChildrenPrice();
         default:
            throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
      }
   }

   private double computeChildrenPrice() {
      double price;
      price = 1.5;
      if (getDaysRented() > 3)
         price += (getDaysRented() - 3) * 1.5;
      return price;
   }

   private double computeNewReleasePrice() {
      double price;
      price = getDaysRented() * 3;
      return price;
   }

   private double computeRegularPrice() {
      double price;
      price = 2;
      if (getDaysRented() > 2)
         price += (getDaysRented() - 2) * 1.5;
      return price;
   }
}

class Customer {

   private final String name;
   private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order
   private final List<Rental> rentalList = new ArrayList<>();

   public Customer(String name) {
      this.name = name;
   }

   public void addRental(Movie movie, int daysRented) {
      rentals.put(movie, daysRented);
      rentalList.add(new Rental(movie, daysRented));
   }

   public String getName() {
      return name;
   }

   public String formatStatement() {
      return formatHeader()
             + formatBody()
             + formatFooter();
   }


//   private LocalDate localDate;
////   private String localDateStr;
//
//   public Customer setLocalDate(LocalDate localDate) {
//      this.localDate = localDate;
//      this.localDateStr = localDate.toString();
//      return this;
//   }
//
//   public String getLocalDateStr() {
//      return localDate.toString();
//   }

   private String formatFooter() {
      return "Amount owed is " + getTotalPrice() + "\n"
             + "You earned " + getTotalRenterPoints() + " frequent renter points";
   }

   private int getTotalRenterPoints() {
      return rentalList.stream().mapToInt(Rental::computeRenterPoints).sum();
   }

   private double getTotalPrice() {
      return rentalList.stream().mapToDouble(Rental::getPrice).sum();
   }

   private String formatBody() {
      return rentalList.stream().map(this::formatStatementLine).collect(joining());
   }

   private String formatHeader() {
      return "Rental Record for " + getName() + "\n";
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + rental.getPrice() + "\n";
   }

   // TODO un obiect Statement care sa aiba functie de "renderAsString()"

}