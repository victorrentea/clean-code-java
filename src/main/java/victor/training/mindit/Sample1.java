package victor.training.mindit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.toList;
import static victor.training.mindit.BigUtils.smartEquals;
import static victor.training.mindit.ConditionType.Turnover;
import static victor.training.mindit.DiscountUnit.Percentage;

@Slf4j
public class Sample1 {

   public static final BigDecimal ROUND_B = BigDecimal.valueOf(5);

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
      mechanic.capThePercentageOff(type);
      return tryComputeMarketingCommunication_(mechanic, currencyList, type);

   }

   private static String tryComputeMarketingCommunication_(MechanicDTO mechanic, List<String> currencyList, MechanicType type) {
      String comMsg = "";
      String optionA = "";
      String optionB = "";
      BigDecimal percentage = mechanic.getPercentageOff().orElse(ZERO);
      BigDecimal displayedPercentage = round(percentage);


      switch (type) {
         case DISCOUNT:
            CommunicationView dto = computeDiscountCommunication(percentage, displayedPercentage);
            comMsg = dto.getComMsg();
            optionA = dto.getOptionA();
            optionB = dto.getOptionB();
            break;
         case PROMOTION_PRICE:
            optionA = currencyList.stream().map(cur -> "Only xx.xx " + cur).collect(Collectors.joining(", "));
            optionB = currencyList.stream().map(cur -> "Special Price xx.xx " + cur).collect(Collectors.joining(", "));
            break;
         case MULTI_UNIT_SIMQTY_LOWEST_PRICE:
            CommunicationView.withOptionA("Buy Item A, Item B, Item C,...Item N and get the lowest price for free.");
            break;
         case MULTI_UNIT_SIMQTY_HIGHEST_PRICE:
            optionA = "Buy Item A, Item B, Item C,...Item N and get the highest price for free.";
            break;
         case MULTI_UNIT_SIMQTY_EVERY_ITEM: {
            if (percentage.compareTo(BigDecimal.TEN) < 0) {
               comMsg = "No percentage below 10% can be displayed in the communication - " +
                        "Please communicate the saving amount and final price in local currency instead";
            } else if (smartEquals(mechanic.getNoItems(), 3) && isOneThird(percentage)) {
               optionA = "Buy item A, B, ...N & get the lowest one for Free";
            } else if (smartEquals(mechanic.getNoItems(), 2) && smartEquals(percentage, 25)) {
               optionA = "Buy 2 & get 50% off the 2nd item";
            } else {
               optionA = "Buy item A, B, ...N and SAVE " + displayedPercentage + "% on the lowest price";
            }
            break;
         }
         case MULTI_UNIT_FIXED_COMBINATION: {
            if (mechanic.getHasGiftOutsideAssortment()) {
               optionA = "FREE Gift when you buy Item A & Item B";
               optionB = "Buy Item A & Item B & receive a FREE Gift";
            } else if (percentage.compareTo(ZERO) != 0) {
               if (percentage.compareTo(BigDecimal.TEN) < 0) {
                  comMsg = "No percentage below 10% can be displayed in the communication - " +
                           "Please communicate the saving amount and final price in local currency instead";
               } else if (mechanic.getHasGiftInAssortment()) {
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
            BigDecimal discoutDisplayPercentage = round(discountPercentage);
            BigDecimal discountValue; // _self.promotionData.mechanic.varioDiscountValue[currency];
            List<String> partsOptionA = new ArrayList<>();
            List<String> partsOptionB = new ArrayList<>();

//            String optionA;
//            String optionB;
//            String comMsg;
            if (conditionType == ConditionType.Quantity) {
               if (mechanic.getHasGiftOutsideAssortment()) {
                  optionA = "FREE Gift when you buy " + conditionQuantity + " products Multi-Unit";
                  optionB = "Buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit";
               } else if (discountUnit == DiscountUnit.Value) {
                  for (String currency : currencyList) {
                     conditionValue = mechanic.getVarioDiscountValue().get(currency);
                     if (mechanic.getHasGiftInAssortment()) {
                        partsOptionA.add("SAVE " + currency + (conditionQuantity.multiply(conditionValue)) + " when you buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit");
                        partsOptionB.add("Buy " + conditionQuantity + " & SAVE " + currency + conditionValue + " on each product & receive a FREE Gift Multi-Unit");
                     } else if (!(smartEquals(mechanic.getNoItems(), 3) && isOneThird(percentage)) &&
                                !(smartEquals(mechanic.getNoItems(), 2) && smartEquals(percentage, 25))) {
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
                  } else if (mechanic.getHasGiftInAssortment()) {
                     if (smartEquals(mechanic.getNoItems(), 3) && isOneThird(percentage)) {
                        optionA = "Buy 2, get 1 item FREE and get a GIFT";
                     } else if (smartEquals(mechanic.getNoItems(), 2) && smartEquals(percentage, 25)) {
                        optionA = "Buy 2, get 50% off the 2nd item and get a GIFT";
                     } else {
                        optionA = "SAVE " + discoutDisplayPercentage + "% when you buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit";
                        optionB = "Buy " + conditionQuantity + " & SAVE " + discoutDisplayPercentage + "% on each product & receive a FREE Gift Multi-Unit";
                     }
                  } else {
                     if (smartEquals(mechanic.getNoItems(), 3) && isOneThird(percentage)) {
                        optionA = "Buy 2 & get 1 item FREE";
                     } else if (smartEquals(mechanic.getNoItems(), 2) && smartEquals(percentage, 25)) {
                        optionA = "Buy 2 & get 50% off the 2nd item";
                     } else {
                        optionA = "Buy " + conditionQuantity + " & SAVE " + discoutDisplayPercentage + "% Multi-Unit";
                        optionB = "SAVE " + discoutDisplayPercentage + "% when you buy " + conditionQuantity + " Multi-Unit";
                     }
                  }
               }
            } else if (conditionType == Turnover) {
               if (mechanic.getHasGiftOutsideAssortment()) {
                  for (String currency : currencyList) {
                     partsOptionA.add("FREE Gift for each " + currency + mechanic.getVarioDiscountValue().get(currency) + " spent on X");
                  }
                  optionA = String.join("\n", partsOptionA);
               } else {
                  if (discountUnit == DiscountUnit.Value) {
                     for (String currency : currencyList) {
                        discountValue = mechanic.getVarioDiscountValue().get(currency);
                        conditionValue = mechanic.getVarioConditionValueTurnover().get(currency);
                        if (mechanic.getHasGiftInAssortment()) {
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
                        for (String currency : currencyList) {
                           conditionValue = mechanic.getVarioConditionValueTurnover().get(currency);
                           if (mechanic.getHasGiftInAssortment()) {
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
            BigDecimal noItems2 = mechanic.getPoolConditionValueQuantity();
            List<String> partOptionA = new ArrayList<>();
            String currency;
            for (String s : currencyList) {
               currency = s;
               BigDecimal df = mechanic.getPoolDiscountValueDF().get(currency);
               BigDecimal dp = mechanic.getPoolDiscountValueDP().get(currency);

               if (df.compareTo(ZERO) > 0 && dp.compareTo(ZERO) > 0) {
                  partOptionA.add(noItems2 + " for " + currency + df + " DutyFree and " + currency + dp + " DutyPaid Multi-Unit");
               } else if (dp.compareTo(ZERO) > 0) {
                  partOptionA.add(noItems2 + " for " + currency + dp + " DutyPaid Multi-Unit");
               } else if (df.compareTo(ZERO) > 0) {
                  partOptionA.add(noItems2 + " for " + currency + df + " DutyFree Multi-Unit");
               }
            }

            optionA = String.join("\n", partOptionA);
            break;
         }
         case MULTI_UNIT_LAYERED_COMBINATION: {
            if (mechanic.getLayerType() == LayerType.Quantity_Value) {
               List<String> partsOptionA = new ArrayList<>();
               for (String currency : currencyList) {
                  List<String> parts = getCommunicationPartsForCurrency(currency, mechanic.getValueLayers());
                  if (parts.size() > 0) {
                     partsOptionA.add(String.join(", ", parts) + " Multi-Unit");
                  }
               }
               optionA = String.join("\n", partsOptionA);
            } else if (mechanic.getLayerType() == LayerType.Quantity_Percentage) {
               List<String> partsOptionA = mechanic.getPercentageLayers().stream()
                   .map(layer -> "Buy " + layer.getQuantity() + " & SAVE " + round(layer.getPercentage()) + "%")
                   .collect(toList());
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

      return new CommunicationView(comMsg, optionA, optionB).asFormattedMessage();
   }

   private static CommunicationView computeDiscountCommunication(BigDecimal percentage, BigDecimal displayedPercentage) {
      if (percentage.compareTo(BigDecimal.TEN) < 0) {
         return CommunicationView.withMessage("No percentage below 10% can be displayed in the communication - " +
                                              "Please communicate the saving amount and final price in local currency instead");
      } else {
         return CommunicationView.withOptions("SAVE " + displayedPercentage + "%", displayedPercentage + "% Off");
      }
   }

   private static BigDecimal round(BigDecimal percentage) {
      return percentage.divide(ROUND_B, BigDecimal.ROUND_FLOOR).multiply(ROUND_B);
   }

   private static List<String> getCommunicationPartsForCurrency(String currency, List<Layer> layers) {
      return layers.stream()
          .filter(layer -> layer.getDutyForCurrency(currency).hasSomeDuty())
          .map(layer -> {
             Duty duty = layer.getDutyForCurrency(currency);
             if (duty.getDutyFree().compareTo(ZERO) > 0 && duty.getDutyPaid().compareTo(ZERO) > 0) {
                return layer.getQuantity() + " for " + currency + layer.getValues().get(currency).getDutyFree() + " DutyFree and "
                       + currency + layer.getValues().get(currency).getDutyPaid() + " DutyPaid";
             } else if (duty.getDutyPaid().compareTo(ZERO) > 0) {
                return layer.getQuantity() + " for " + currency + layer.getValues().get(currency).getDutyPaid() + " DutyPaid";
             } else if (duty.getDutyFree().compareTo(ZERO) > 0) {
                return layer.getQuantity() + " for " + currency + layer.getValues().get(currency).getDutyFree() + " DutyFree";
             } else {
                return "";
             }
          })
          .collect(toList());
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