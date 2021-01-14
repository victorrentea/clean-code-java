package victor.training;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MechanicDTO {
   private BigDecimal per;

   public MechanicType getSelectedMechanicType() {
      return null;
   }

   public Optional<BigDecimal> getPercentageOff() {
      return Optional.ofNullable(per);
   }






   public void setPercentageOff(BigDecimal max) {
   }

   public BigDecimal getNoItems() {
      return null;
   }

   public boolean getHasGiftInAssortment() {
      return false;
   }

   public boolean getHasGiftOutsideAssortment() {
      return false;
   }

   public ConditionType getVarioConditionType() {
      return null;
   }

   public BigDecimal getVarioConditionValueQuantity() {
      return null;
   }


   public DiscountUnit getVarioDiscountUnit() {
      return null;
   }

   public BigDecimal getVarioDiscountPercentage() {
      return null;
   }

   public Map<String, BigDecimal> getVarioDiscountValue() {
      return null;
   }

   public Map<String, BigDecimal> getVarioConditionValueTurnover() {
      return null;
   }

   public BigDecimal getPoolConditionValueQuantity() {
      return null;
   }

   public Map<String, BigDecimal>  getPoolDiscountValueDF() {
      return Collections.emptyMap();
   }

   public Map<String, BigDecimal>  getPoolDiscountValueDP() {
      return null;
   }

   public LayerType getLayerType() {
      return null;
   }

   public List<Layer> getPercentageLayers() {
      return null;
   }

   public List<Layer> getValueLayers() {
      return null;
   }
}
