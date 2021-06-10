package videostore.horror;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

@Service
public class PriceService {
   @Autowired
   SomeService someService;

   public Double calculatePrice(Rental rental) {
      Category category = rental.getMovie().getCategory();

      return category.getPriceAlgo().apply(this, rental.getDaysRented());
   }


   public  double computeNewReleasePrice(int daysRented) {
      return daysRented * 3;
   }

   public double computeChildrenPrice(int daysRented) {
      double price;
      price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;
      return price;
   }

   public double calculateRegularPrice(int daysRented) {
      double price;
      price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
}
