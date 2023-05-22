package videostore.horror;

import org.springframework.beans.factory.annotation.Value;

public class PriceFormulas {
//  @Value("${asdas}")
//  private int factor;
  public double computeChildrenPrice(int daysRented) {
    double result = 1.5;
    if (daysRented > 3)
      result += (daysRented - 3) * 1.5;
    return result;
  }
}
