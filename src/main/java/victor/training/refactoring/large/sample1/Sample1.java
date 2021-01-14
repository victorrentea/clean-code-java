package victor.training.refactoring.large.sample1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static victor.training.refactoring.large.sample1.ConditionType.Turnover;
import static victor.training.refactoring.large.sample1.DiscountUnit.Percentage;

@Slf4j
public class Sample1 {
   //moved from FE side
   public static String computeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList, boolean b) {
      MechanicType type = mechanic.getSelectedMechanicType();
      if (type == null || CollectionUtils.isEmpty(currencyList)) {
         return "";
      }

      String comMsg = "";
      String optionA = "";
      String optionB = "";

      BigDecimal percentage = mechanic.getPercentageOff();

      if (percentage == null) {
         percentage = BigDecimal.ZERO;
      }

      try {

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
            case MULTI_UNIT_SIMQTY_EVERY_ITEM:
               if (percentage.compareTo(BigDecimal.TEN) < 0) {
                  comMsg = "No percentage below 10% can be displayed in the communication - " +
                           "Please communicate the saving amount and final price in local currency instead";
               } else if (noItems.compareTo(BigDecimal.valueOf(3)) == 0 && percentage.compareTo(BigDecimal.valueOf(33.33)) == 0) {
                  optionA = "Buy item A, B, ...N & get the lowest one for Free";
               } else if (noItems.compareTo(BigDecimal.valueOf(2)) == 0 && percentage.compareTo(BigDecimal.valueOf(25)) == 0) {
                  optionA = "Buy 2 & get 50% off the 2nd item";
               } else {
                  optionA = "Buy item A, B, ...N and SAVE " + displayedPercentage + "% on the lowest price";
               }
               break;
            case MULTI_UNIT_FIXED_COMBINATION:
               // console.log('ENUM.promotionType.multiUnit_fixed');
               if (hasGiftOut) {
                  optionA = "FREE Gift when you buy Item A & Item B";
                  optionB = "Buy Item A & Item B & receive a FREE Gift";
               } else if (percentage.compareTo(BigDecimal.ZERO) != 0) {
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
            case MULTI_UNIT_VARIO_COMBINATION:

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
                        } else {
                           if (noItems.compareTo(BigDecimal.valueOf(3)) == 0 && percentage.compareTo(BigDecimal.valueOf(33.33)) == 0) {
                              optionA = "Buy 2 & get 1 FREE";
                           } else if (noItems.compareTo(BigDecimal.valueOf(2)) == 0 && percentage.compareTo(BigDecimal.valueOf(25)) == 0) {
                              optionA = "Buy 2 & get 50% off the 2nd item";
                           } else {
                              partsOptionA.add("SAVE " + currency + (conditionQuantity.multiply(conditionValue)) + " when you buy " + conditionQuantity + " Multi-Unit");
                              partsOptionB.add("Buy " + conditionQuantity + " & SAVE " + currency + conditionValue + " on each product Multi-Unit");
                           }
                        }
                     }
                     optionA = partsOptionA.stream().collect(Collectors.joining("\n"));
                     optionB = partsOptionB.stream().collect(Collectors.joining("\n"));
                  } else if (discountUnit == DiscountUnit.Percentage) {
                     if (discountPercentage.compareTo(BigDecimal.TEN) < 0) {
                        comMsg = "No percentage below 10% can be displayed in the communication - " +
                                 "Please communicate the saving amount and final price in local currency instead";
                     } else if (hasGiftIn) {
                        if (noItems.compareTo(BigDecimal.valueOf(3)) == 0 && percentage.compareTo(BigDecimal.valueOf(33.33)) == 0) {
                           optionA = "Buy 2, get 1 item FREE and get a GIFT";
                        } else if (noItems.compareTo(BigDecimal.valueOf(2)) == 0 && percentage.compareTo(BigDecimal.valueOf(25)) == 0) {
                           optionA = "Buy 2, get 50% off the 2nd item and get a GIFT";
                        } else {
                           optionA = "SAVE " + discoutDisplayPercentage + "% when you buy " + conditionQuantity + " products & receive a FREE Gift Multi-Unit";
                           optionB = "Buy " + conditionQuantity + " & SAVE " + discoutDisplayPercentage + "% on each product & receive a FREE Gift Multi-Unit";
                        }
                     } else {
                        if (noItems.compareTo(BigDecimal.valueOf(3)) == 0 && percentage.compareTo(BigDecimal.valueOf(33.33)) == 0) {
                           optionA = "Buy 2 & get 1 item FREE";
                        } else if (noItems.compareTo(BigDecimal.valueOf(2)) == 0 && percentage.compareTo(BigDecimal.valueOf(25)) == 0) {
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
                     optionA = partsOptionA.stream().collect(Collectors.joining("\n"));
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
                        optionA = partsOptionA.stream().collect(Collectors.joining("\n"));
                        optionB = partsOptionB.stream().collect(Collectors.joining("\n"));
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
                           optionA = partsOptionA.stream().collect(Collectors.joining("\n"));
                        }
                     }
                  }
               }
               break;
            case MULTI_UNIT_POOL_COMBINATION:
               noItems = mechanic.getPoolConditionValueQuantity();
               List<String> partOptionA = new ArrayList();
               for (i = 0; i < currencyList.size(); i++) {
                  currency = currencyList.get(i);
                  BigDecimal df = mechanic.getPoolDiscountValueDF().get(currency);
                  BigDecimal dp = mechanic.getPoolDiscountValueDP().get(currency);

                  if (df.compareTo(BigDecimal.ZERO) > 0 && dp.compareTo(BigDecimal.ZERO) > 0) {
                     partOptionA.add(noItems + " for " + currency + df + " DutyFree and " + currency + dp + " DutyPaid Multi-Unit");
                  } else if (dp.compareTo(BigDecimal.ZERO) > 0) {
                     partOptionA.add(noItems + " for " + currency + dp + " DutyPaid Multi-Unit");
                  } else if (df.compareTo(BigDecimal.ZERO) > 0) {
                     partOptionA.add(noItems + " for " + currency + df + " DutyFree Multi-Unit");
                  }
               }

               optionA = partOptionA.stream().collect(Collectors.joining("\n"));
               break;
            case MULTI_UNIT_LAYERED_COMBINATION:
               partsOptionA = new ArrayList<>();
               if (mechanic.getLayerType() == LayerType.Quantity_Value) {
                  for (i = 0; i < currencyList.size(); i++) {
                     currency = currencyList.get(i);
                     List<String> parts = new ArrayList<>();
                     String finalCurrency = currency;
                     mechanic.getValueLayers().forEach(layer -> {
                        BigDecimal df = layer.getValues().get(finalCurrency).getPriceDF();
                        BigDecimal dp = layer.getValues().get(finalCurrency).getPriceDP();
                        if (df.compareTo(BigDecimal.ZERO) > 0 && dp.compareTo(BigDecimal.ZERO) > 0) {
                           parts.add(layer.getQuantity() + " for " + finalCurrency + layer.getValues().get(finalCurrency).getPriceDF() + " DutyFree and "
                                     + finalCurrency + layer.getValues().get(finalCurrency).getPriceDP() + " DutyPaid");
                        } else if (dp.compareTo(BigDecimal.ZERO) > 0) {
                           parts.add(layer.getQuantity() + " for " + finalCurrency + layer.getValues().get(finalCurrency).getPriceDP() + " DutyPaid");
                        } else if (df.compareTo(BigDecimal.ZERO) > 0) {
                           parts.add(layer.getQuantity() + " for " + finalCurrency + layer.getValues().get(finalCurrency).getPriceDF() + " DutyFree");
                        }
                     });
                     if (parts.size() > 0)
                        partsOptionA.add(parts.stream().collect(Collectors.joining(", ")) + " Multi-Unit");
                  }
                  optionA = partsOptionA.stream().collect(Collectors.joining("\n"));
               } else if (mechanic.getLayerType() == LayerType.Quantity_Percentage) {
                  mechanic.getPercentageLayers().forEach(layer -> {
                     BigDecimal displayedLayerPercentage = layer.getPercentage().divide(roundB, BigDecimal.ROUND_FLOOR).multiply(roundB);
                     partsOptionA.add("Buy " + layer.getQuantity() + " & SAVE " + displayedLayerPercentage + "%");

                  });
                  if (partsOptionA.size() > 0)
                     optionA = partsOptionA.stream().collect(Collectors.joining(", ")) + " Multi-Unit";
               }
               break;
            case MULTI_UNIT_PACK_AND_COMBO:
            case NON_VALUE_NO_DISCOUNT:
            case NON_VALUE_SMALL:
            case NON_VALUE_MEDIUM:
            case NON_VALUE_LARGE:
            default:
               break;
         }
      } catch (Exception ex) {
         log.error("Error during marketing communication generation"+ex);
         return "";
      }

      String marketingCommunication;
      if (StringUtils.isNotEmpty(comMsg)) {
         marketingCommunication = comMsg;
      } else if (StringUtils.isNotEmpty(optionA) && StringUtils.isNotEmpty(optionB)) {
         marketingCommunication = "Option 1: " + optionA + "\nOption 2: " + optionB;
      } else if (StringUtils.isNotEmpty(optionA)) {
         marketingCommunication = optionA;
      } else {
         marketingCommunication = "";
      }

      return marketingCommunication;
   }
}