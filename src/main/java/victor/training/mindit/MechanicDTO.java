package victor.training.mindit;

import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.ZERO;

public class MechanicDTO {
   private BigDecimal per;

   public void capThePercentageOff(MechanicType type) {
      BigDecimal max = BigDecimal.valueOf(type == MechanicType.MULTI_UNIT_SIMQTY_EVERY_ITEM ? 100 : 99);
      if (getPercentageOff().orElse(ZERO).compareTo(max) > 0) {
         setPercentageOff(max);
      }
   }

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

   public void setVarioConditionValueTurnover(HashMap<Object, Object> objectObjectHashMap) {

   }

   public void setVarioDiscountValue(HashMap<Object, Object> objectObjectHashMap) {
   }
}
