package videostore.dirty;


import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

@Service
public class PriceService {
//   @Autowired
//   VreoDep

   public double calculateChildrenPrice(int daysRented) {
      double price = 1.5;
      if (daysRented > 3)
         price += (daysRented - 3) * 1.5;;
      return price;
   }
   public double calculateRegularPrice(int daysRented) {
      double price = 2;
      if (daysRented > 2)
         price += (daysRented - 2) * 1.5;
      return price;
   }
   public double calculateNewReleasePrice(int daysRented) {
     return daysRented * 3;
   }
}
