package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer
{
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer( String name )
  {
    this.name = name;
  }

  public void addRental( Movie movie, int days )
  {
    rentals.add( new Rental( movie, days ) );
  }

  public String rentMovies()
  {
    String rentedList = rentals.stream().map( Rental::toString ).collect( Collectors.joining() );
    return "Rental Record for " + name + "\n" + rentedList +
      "Amount owed is " + getTotalAmount() + "\n" +
      "You earned " + addFrequentRenterPoints() + " frequent renter points";
  }

  private double getTotalAmount()
  {
    return rentals.stream().mapToDouble( Rental::cost ).sum();
  }

  private int addFrequentRenterPoints()
  {
    return rentals.stream().mapToInt( Rental::loyaltyPoints ).sum();
  }
}