package victor.training.refactoring.incubator;

public class OrderLineParser {

   public OrderLine parse(String orderString) {
      String[] orderData = orderString.split("\\s+");
      String productCode = orderData[0].split("-")[1];
      int itemCount = Integer.parseInt(orderData[1]);
      return new OrderLine(productCode, itemCount);
   }
}