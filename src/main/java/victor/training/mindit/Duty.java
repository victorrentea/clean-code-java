package victor.training.mindit;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class Duty {
   private final BigDecimal dutyFree;
   private final BigDecimal dutyPaid;

   public Duty(BigDecimal dutyFree, BigDecimal dutyPaid) {
      this.dutyFree = dutyFree;
      this.dutyPaid = dutyPaid;
   }

   public boolean hasSomeDuty() {
      return dutyFree.compareTo(ZERO) > 0 || getDutyPaid().compareTo(ZERO) > 0;
   }

   public BigDecimal getDutyFree() {
      return dutyFree;
   }

   public BigDecimal getDutyPaid() {
      return dutyPaid;
   }
}
