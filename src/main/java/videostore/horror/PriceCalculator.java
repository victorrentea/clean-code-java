//package videostore.horror;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Service;
//
//
//public interface PriceCalculator {
//  double computePrice(int daysRented);
//}
//
//@Service
//class PriceCalculatorFormulas {
//
//  public Double computePrice(Rental rental) {
//    return rental.getMovie().priceCode().priceFormula.apply(this, rental.getDays());
//  }
//
//
//   @Value("${factorDinProperties}")
//   private int factor;
//  //5 linii de cod util ???!!! cam putin...
//
//  public double computeRegularPrice(int daysRented) {
//    double price = 2;
//    if (daysRented > 2)
//      price += (daysRented - 2) * 1.5;
//    return price;
//  }
//
//  public double computeNewReleasePrice(int daysRented) {
//    return daysRented * factor;
//  }
//
//  public double computeChildrenPrice(int daysRented) {
//    double price = 1.5;
//    if (daysRented > 3)
//      price += (daysRented - 3) * 1.5;
//    return price;
//  }
//}
