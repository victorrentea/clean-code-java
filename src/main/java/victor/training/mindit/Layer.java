package victor.training.mindit;

import java.math.BigDecimal;
import java.util.Map;

public class Layer {
   public Duty getDutyForCurrency(String currency) {
      BigDecimal dutyFree = getValues().get(currency).getDutyFree();
      BigDecimal dutyPaid = getValues().get(currency).getDutyPaid();
      return new Duty(dutyFree, dutyPaid);
   }

   public BigDecimal getPercentage() {
      return null;
   }

   public Map<String, Duty> getValues() {
      return null;
   }

   public int getQuantity() {
      return 0;
   }
}
