package victor.training;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static victor.training.BigUtils.smartEquals;
import static victor.training.ConditionType.Turnover;
import static victor.training.DiscountUnit.Percentage;

@Slf4j
public class Sample1 {

   //moved from FE side
   public static String computeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList) {
      MechanicType type = mechanic.getSelectedMechanicType();
      if (type == null || CollectionUtils.isEmpty(currencyList)) {
         return "";
      }
      try {
         return tryComputeMarketingCommunication(mechanic, currencyList, type);
      } catch (Exception ex) {
         log.error("Error during marketing communication generation" + ex);
         return "";
      }
   }

   private static String tryComputeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList, MechanicType type) {
      String comMsg = "";
      String optionA = "";
      String optionB = "";

      BigDecimal percentage = mechanic.getPercentageOff().orElse(ZERO);

      BigDecimal max = BigDecimal.valueOf(type == MechanicType.MULTI_UNIT_SIMQTY_EVERY_ITEM ? 100 : 99);
      if (percentage.compareTo(max) > 0) {
         mechanic.setPercentageOff(max);
         percentage = max;
      }

      BigDecimal roundB = BigDecimal.valueOf(5);
      BigDecimal displayedPercentage = percentage.divide(roundB, BigDecimal.ROUND_FLOOR).multiply(roundB);

      BigDecimal noItems = mechanic.getNoItems();
      boolean hasGiftIn = mechanic.getHasGiftInAssortment();
      boolean hasGiftOut = mechanic.getHasGiftOutsideAssortment();


      switch (type) {
         case DISCOUNT:
            if (percentage.compareTo(BigDecimal.TEN) < 0) {
               comMsg = "No percentage below 10% can be displayed in the communication - " +
                        "Please communicate the saving amount and final price in local currency instead";
            } else {
               optionA = "SAVE " + displayedPercentage + "%";
               optionB = displayedPercentage + "% Off";
            }
            break;
         case PROMOTION_PRICE:
            optionA = currencyList.stream().map(cur -> "Only xx.xx " + cur).collect(Collectors.joining(", "));
            optionB = currencyList.stream().map(cur -> "Special Price xx.xx " + cur).collect(Collectors.joining(", "));
            break;
         case MULTI_UNIT_SIMQTY_LOWEST_PRICE:
            optionA = "Buy Item A, Item B, Item C,...Item N and get the lowest price for free.";
            break;
         case MULTI_UNIT_SIMQTY_HIGHEST_PRICE:
            optionA = "Buy Item A, Item B, Item C,...Item N and get the highest price for free.";
            break;
         case MULTI_UNIT_SIMQTY_EVERY_ITEM: {
            if (percentage.compareTo(BigDecimal.TEN) < 0) {
               comMsg = "No percentage below 10% can be displayed in the communication - " +
                        "Please communicate the saving amount and final price in local currency instead";
            } else if (smartEquals(noItems, 3) && isOneThird(percentage)) {
               optionA = "Buy item A, B, ...N & get the lowest one for Free";
            } else if (smartEquals(noItems, 2) && smartEquals(percentage, 25)) {
               optionA = "Buy 2 & get 50% off the 2nd item";
            } else {
               optionA = "Buy item A, B, ...N and SAVE " + displayedPercentage + "% on the lowest price";
            }
            break;
         }
         case MULTI_UNIT_FIXED_COMBINATION: {
            if (hasGiftOut) {
               optionA = "FREE Gift when you buy Item A & Item B";
               optionB = "Buy Item A & Item B & receive a FREE Gift";
            } else if (percentage.compareTo(ZERO) != 0) {
               if (percentage.compareTo(BigDecimal.TEN) < 0) {
                  comMsg = "No percentage below 10% can be displayed in the communication - " +
                           "Please communicate the saving amount and final price in local currency instead";
               } else if (hasGiftIn || hasGiftOut) {
                  optionA = "SAVE " + displayedPercentage + "% when you buy Item A & Item B & receive a FREE Gift";
               } else {
                  optionA = "SAVE " + displayedPercentage + "% when you buy Item A & Item B";
               }
            }
            break;
         }
         case MULTI_UNIT_VARIO_COMBINATION: {
            ConditionType conditionType = mechanic.getVarioConditionType();
            BigDecimal conditionQuantity = mechanic.getVarioConditionValueQuantity();
            DiscountUnit discountUnit;
            BigDecimal conditionValue; // _self.promotionData.mechanic.varioConditionValueTurnover[currency]
            discountUnit = mechanic.getVarioDiscountUnit();
            BigDecimal discountPercentage = mechanic.getVarioDiscountPercentage();
            BigDecimal discoutDisplayPercentage = discountPercentage.divide(roundB, BigDecimal.ROUND_FLOOR).multiply(roundB);
            BigDecimal discountValue; // _self.promotionData.mechanic.varioDiscountValue[currency];
            int i = 0;
            List<String> partsOptionA = new ArrayList<>();
            List<String> partsOptionB = new ArrayList<>();

            String currency;
            if (conditionType == ConditionType.Quantity) {
               if (hasGiftOut) {
                  optionA = "FREE Gift when you buy " + conditionQuantity + " products Multi-Unit";
                  optionB = "Buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit";
               } else if (discountUnit == DiscountUnit.Value) {
                  for (i = 0; i < currencyList.size(); i++) {
                     currency = currencyList.get(i);
                     conditionValue = mechanic.getVarioDiscountValue().get(currency);
                     if (hasGiftIn) {
                        partsOptionA.add("SAVE " + currency + (conditionQuantity.multiply(conditionValue)) + " when you buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit");
                        partsOptionB.add("Buy " + conditionQuantity + " & SAVE " + currency + conditionValue + " on each product & receive a FREE Gift Multi-Unit");
                     } else if (!(smartEquals(noItems, 3) && isOneThird(percentage)) &&
                                !(smartEquals(noItems, 2) && smartEquals(percentage, 25))) {


                        partsOptionA.add("SAVE " + currency + (conditionQuantity.multiply(conditionValue)) + " when you buy " + conditionQuantity + " Multi-Unit");
                        partsOptionB.add("Buy " + conditionQuantity + " & SAVE " + currency + conditionValue + " on each product Multi-Unit");
                     }
                  }
                  optionA = String.join("\n", partsOptionA);
                  optionB = String.join("\n", partsOptionB);
               } else if (discountUnit == Percentage) {
                  if (discountPercentage.compareTo(BigDecimal.TEN) < 0) {
                     comMsg = "No percentage below 10% can be displayed in the communication - " +
                              "Please communicate the saving amount and final price in local currency instead";
                  } else if (hasGiftIn) {
                     if (smartEquals(noItems, 3) && isOneThird(percentage)) {
                        optionA = "Buy 2, get 1 item FREE and get a GIFT";
                     } else if (smartEquals(noItems, 2) && smartEquals(percentage, 25)) {
                        optionA = "Buy 2, get 50% off the 2nd item and get a GIFT";
                     } else {
                        optionA = "SAVE " + discoutDisplayPercentage + "% when you buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit";
                        optionB = "Buy " + conditionQuantity + " & SAVE " + discoutDisplayPercentage + "% on each product & receive a FREE Gift Multi-Unit";
                     }
                  } else {
                     if (smartEquals(noItems, 3) && isOneThird(percentage)) {
                        optionA = "Buy 2 & get 1 item FREE";
                     } else if (smartEquals(noItems, 2) && smartEquals(percentage, 25)) {
                        optionA = "Buy 2 & get 50% off the 2nd item";
                     } else {
                        optionA = "Buy " + conditionQuantity + " & SAVE " + discoutDisplayPercentage + "% Multi-Unit";
                        optionB = "SAVE " + discoutDisplayPercentage + "% when you buy " + conditionQuantity + " Multi-Unit";
                     }
                  }
               }
            } else if (conditionType == Turnover) {
               if (hasGiftOut) {
                  for (i = 0; i < currencyList.size(); i++) {
                     currency = currencyList.get(i);
                     discountValue = mechanic.getVarioDiscountValue().get(currency);
                     partsOptionA.add("FREE Gift for each " + currency + discountValue + " spent on X");
                  }
                  optionA = String.join("\n", partsOptionA);
               } else {
                  if (discountUnit == DiscountUnit.Value) {
                     for (i = 0; i < currencyList.size(); i++) {

                        currency = currencyList.get(i);
                        discountValue = mechanic.getVarioDiscountValue().get(currency);
                        conditionValue = mechanic.getVarioConditionValueTurnover().get(currency);
                        if (hasGiftIn) {
                           partsOptionA.add("SAVE " + currency + discountValue + " for each " + currency + conditionValue + " spent on X & receive a FREE Gift");
                           partsOptionB.add("SAVE " + currency + discountValue + " & receive a FREE Gift for each " + currency + conditionValue + " spent");
                        } else {
                           partsOptionA.add("SAVE " + currency + discountValue + " for each " + currency + conditionValue + " spent on X");
                        }
                     }
                     optionA = String.join("\n", partsOptionA);
                     optionB = String.join("\n", partsOptionB);
                  } else if (discountUnit == Percentage) {
                     if (discountPercentage.compareTo(BigDecimal.TEN) < 0) {
                        comMsg = "No percentage below 10% can be displayed in the communication - " +
                                 "Please communicate the saving amount and final price in local currency instead";
                     } else {
                        for (i = 0; i < currencyList.size(); i++) {
                           currency = currencyList.get(i);
                           conditionValue = mechanic.getVarioConditionValueTurnover().get(currency);
                           if (hasGiftIn) {
                              partsOptionA.add("SAVE " + discoutDisplayPercentage + "% when you spend " + currency + conditionValue + " on X & receive a FREE Gift");
                           } else {
                              partsOptionA.add("SAVE " + discoutDisplayPercentage + "% for each " + currency + conditionValue + " spent on X");
                           }
                        }
                        optionA = String.join("\n", partsOptionA);
                     }
                  }
               }
            }
            break;
         }
         case MULTI_UNIT_POOL_COMBINATION: {
            noItems = mechanic.getPoolConditionValueQuantity(); // Diamands are Girl's best friend
            List<String> partOptionA = new ArrayList<>();
            String currency;
            for (String s : currencyList) {
               currency = s;
               BigDecimal df = mechanic.getPoolDiscountValueDF().get(currency);
               BigDecimal dp = mechanic.getPoolDiscountValueDP().get(currency);

               if (df.compareTo(ZERO) > 0 && dp.compareTo(ZERO) > 0) {
                  partOptionA.add(noItems + " for " + currency + df + " DutyFree and " + currency + dp + " DutyPaid Multi-Unit");
               } else if (dp.compareTo(ZERO) > 0) {
                  partOptionA.add(noItems + " for " + currency + dp + " DutyPaid Multi-Unit");
               } else if (df.compareTo(ZERO) > 0) {
                  partOptionA.add(noItems + " for " + currency + df + " DutyFree Multi-Unit");
               }
            }

            optionA = String.join("\n", partOptionA);
            break;
         }
         case MULTI_UNIT_LAYERED_COMBINATION: {
            List<String> partsOptionA = new ArrayList<>();
            if (mechanic.getLayerType() == LayerType.Quantity_Value) {
               for (String currency : currencyList) {
                  List<String> parts = new ArrayList<>();
                  mechanic.getValueLayers().forEach(layer -> {
                     BigDecimal df = layer.getValues().get(currency).getPriceDF();
                     BigDecimal dp = layer.getValues().get(currency).getPriceDP();
                     if (df.compareTo(ZERO) > 0 && dp.compareTo(ZERO) > 0) {
                        parts.add(layer.getQuantity() + " for " + currency + layer.getValues().get(currency).getPriceDF() + " DutyFree and "
                                  + currency + layer.getValues().get(currency).getPriceDP() + " DutyPaid");
                     } else if (dp.compareTo(ZERO) > 0) {
                        parts.add(layer.getQuantity() + " for " + currency + layer.getValues().get(currency).getPriceDP() + " DutyPaid");
                     } else if (df.compareTo(ZERO) > 0) {
                        parts.add(layer.getQuantity() + " for " + currency + layer.getValues().get(currency).getPriceDF() + " DutyFree");
                     }
                  });
                  if (parts.size() > 0)
                     partsOptionA.add(String.join(", ", parts) + " Multi-Unit");
               }
               optionA = String.join("\n", partsOptionA);
            } else if (mechanic.getLayerType() == LayerType.Quantity_Percentage) {
               mechanic.getPercentageLayers().forEach(layer -> {
                  BigDecimal displayedLayerPercentage = layer.getPercentage().divide(roundB, BigDecimal.ROUND_FLOOR).multiply(roundB);
                  partsOptionA.add("Buy " + layer.getQuantity() + " & SAVE " + displayedLayerPercentage + "%");

               });
               if (partsOptionA.size() > 0) {
                  optionA = String.join(", ", partsOptionA) + " Multi-Unit";
               }
            }
            break;
         }
         case MULTI_UNIT_PACK_AND_COMBO:
         case NON_VALUE_NO_DISCOUNT:
         case NON_VALUE_SMALL:
         case NON_VALUE_MEDIUM:
         case NON_VALUE_LARGE:
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + type);
      }

      if (StringUtils.isNotEmpty(comMsg)) {
         return comMsg;
      } else if (StringUtils.isNotEmpty(optionA) && StringUtils.isNotEmpty(optionB)) {
         return "Option 1: " + optionA + "\nOption 2: " + optionB;
      } else if (StringUtils.isNotEmpty(optionA)) {
         return optionA;
      } else {
         return "";
      }

   }

   private static boolean isOneThird(BigDecimal percentage) {
      return percentage.compareTo(BigDecimal.valueOf(33.33)) == 0;
   }

}

class BigUtils {

   public static boolean smartEquals(BigDecimal a, int b) {
      return a.compareTo(BigDecimal.valueOf(b)) == 0;
   }
}