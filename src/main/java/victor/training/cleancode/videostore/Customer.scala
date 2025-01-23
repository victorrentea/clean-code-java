package victor.training.cleancode.videostore

import victor.training.cleancode.videostore.Movie.REGULAR

import scala.collection.mutable

class Customer(private val name: String) {
  private val rentals = mutable.LinkedHashMap[Movie, Int]() // preserves order of elements TODO find a better way to store this

  def addRental(m: Movie, d: Int): Unit = {
    rentals.put(m, d)
  }

  def getName: String = name

  def statement: String = {
    var totalAmount = 0.0
    var frequentRenterPoints = 0
    var result = s"Rental Record for $name\n"
    // loop over each movie rental
    for ((each, dr) <- rentals) {
      var thisAmount = 0.0
      // determine amounts for every line
      each.getPriceCode() match {
        case REGULAR =>
          thisAmount += 2
          if (dr > 2)
            thisAmount += (dr - 2) * 1.5
        case Movie.NEW_RELEASE =>
          thisAmount += dr * 3
        case Movie.CHILDRENS =>
          thisAmount += 1.5
          if (dr > 3)
            thisAmount += (dr - 3) * 1.5
      }
      // add frequent renter points
      frequentRenterPoints += 1
      // add bonus for a two day new release rental
      if (each.getPriceCode() != null &&
        each.getPriceCode == Movie.NEW_RELEASE &&
        dr > 1)
        frequentRenterPoints += 1
      // show figures line for this rental
      result += s"\t${each.getTitle()}\t$thisAmount\n"
      totalAmount += thisAmount
    }
    // add footer lines
    result += s"Amount owed is $totalAmount\n"
    result += s"You earned $frequentRenterPoints frequent renter points"
    result
  }
}