package videostore.horror;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
      String result = "Rental Record for " + getName() + "\n";

      int frequentRenterPoints = rentalList.stream().mapToInt(this::computeRenterPoints).sum();

//      for (Rental rental : rentalList) {
//         result += formatStatementLine(rental);
//      }
      result += rentalList.stream().map(this::formatStatementLine).collect(joining());
      double totalPrice = rentalList.stream().mapToDouble(this::getPrice).sum();
      // E gresit sa repeti un apel de functie daca :
      // 1) ADUCE ALT REZULTAT LA AL DOILEA APEL: merge in DB (SELECT) si cine stie ce aduce a doua oara (chiar pt aceiasi param) - NU E REFERENTIAL TRANSPARENT ?!?!?!?!
      // 2) FACE SIDE-EFFECTS (ITI TRANSFERA BANI)
      // 3) ia timp

      // O FUNCTIE PURE a) nu face side effects si b) da acelasi rezultat pt aceeasi param
      // daca o fct e pure si fast => o poti chema la liber

      // add footer lines
      result += "Amount owed is " + totalPrice + "\n";
      result += "You earned " + frequentRenterPoints + " frequent renter points";
      return result;
   }

   private String formatStatementLine(Rental rental) {
      return "\t" + rental.getMovie().getTitle() + "\t" + getPrice(rental) + "\n";
   }

   private int computeRenterPoints(Rental rental) {
      if (rental.getMovie().isNewRelease() && rental.getDaysRented() >= 2) {
			return 2;
		} else {
			return 1;
		}
   }

   // TODO un obiect Statement care sa aiba functie de "renderAsString()"

   // fun names lead you to expect the function is PURE "get
   private double getPrice(Rental rental) {
      double price = 0;
      switch (rental.getMovie().getCategory()) {
         case REGULAR:
            price += 2;
            if (rental.getDaysRented() > 2)
               price += (rental.getDaysRented() - 2) * 1.5;
            break;
         case NEW_RELEASE:
            price += rental.getDaysRented() * 3;
            break;
         case CHILDREN:
            price += 1.5;
            if (rental.getDaysRented() > 3)
               price += (rental.getDaysRented() - 3) * 1.5;
            break;
      }
      return price;
   }
}