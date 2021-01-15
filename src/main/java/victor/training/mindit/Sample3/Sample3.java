//package victor.training.mindit.Sample3;
//
//import victor.training.mindit.MechanicDTO;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//import static victor.training.mindit.ConditionType.Quantity;
//import static victor.training.mindit.ConditionType.Turnover;
//import static victor.training.mindit.DiscountUnit.Percentage;
//import static victor.training.mindit.DiscountUnit.Value;
//
//public class Sample3 {
//
//   //moved from FE side
//   public static String computeDescription(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange) {
//
//      if (mechanic.getVarioConditionValueTurnover() == null) {
//         mechanic.setVarioConditionValueTurnover(new HashMap<>());
//      }
//
//      if (mechanic.getVarioDiscountValue() == null) {
//         mechanic.setVarioDiscountValue(new HashMap<>());
//      }
//
//      String description = "";
//
//      //moved from FE
//      if (mechanic.getSelectedMechanicType() != null) {
//         switch (mechanic.getSelectedMechanicType()) {
//            case PROMOTION_PRICE:
//               if (bulkChange) {
//                  description = "Fixed Promo Price";
//               } else {
//                  description = "Special Price";
//               }
//
//               break;
//            case DISCOUNT:
//               description = "Save " + mechanic.getPercentageOff() + "% ";
//               break;
//
//            case MULTI_UNIT_SIMQTY_LOWEST_PRICE:
//               description = "Buy all, get lowest price free";
//               break;
//
//            case MULTI_UNIT_SIMQTY_HIGHEST_PRICE:
//               description = "Buy all items and get the highest price for free ";
//               break;
//
//            case MULTI_UNIT_SIMQTY_EVERY_ITEM:
//               BigDecimal noItem = mechanic.getNoItems();
//               if (noItem == null || mechanic.getPercentageOff() == null) {
//                  break;
//               } else if (new BigDecimal(100).compareTo(mechanic.getPercentageOff().get()) == 0 && noItem != null) {
//                  description = "Buy " + (noItem.intValue() - 1) + " items, get 1 (extra item) for Free ";
//               } else {
//                  description = "Every " + noItem.intValue() + ", " + mechanic.getPercentageOff() + "% Off lowest price ";
//               }
//
//               break;
//
//            case MULTI_UNIT_FIXED_COMBINATION:
//               if (mechanic.getHasGiftInAssortment()) {
//                  description = "Buy all items ";
//               } else {
//                  description = "Buy all, save " + mechanic.getPercentageOff() + "% ";
//               }
//               break;
//
//            case NON_VALUE_NO_DISCOUNT:
//            case NON_VALUE_SMALL:
//            case NON_VALUE_MEDIUM:
//            case NON_VALUE_LARGE:
//               if (bulkChange) {
//                  description = "All offers that are not discounted ";
//               } else {
//                  description = mechanic.getSelectedMechanicType().getDescription();
//               }
//
//               break;
//            case MULTI_UNIT_PACK_AND_COMBO:
//               description = "Pack and Combo Promotion";
//               break;
//
//            case MULTI_UNIT_VARIO_COMBINATION:
//               String currency;
//               if (mechanic.getVarioConditionType() == Quantity && mechanic.getVarioConditionValueQuantity() != null) {
//                  if (mechanic.getHasGiftOutsideAssortment()) {
//                     description = "Buy any " + mechanic.getVarioConditionValueQuantity().intValue() + " items from the list ";
//                  } else {
//                     if (mechanic.getVarioDiscountUnit() == Value) {
//                        for (int i = 0; i < currencyList.size(); i++) {
//                           currency = currencyList.get(i);
//                           description += "Buy any " + mechanic.getVarioConditionValueQuantity().intValue() + ", save " + mechanic.getVarioDiscountValue().get(currency) + " on each ";
//                        }
//                     }
//                     if (mechanic.getVarioDiscountUnit() == Percentage) {
//                        description = "Buy any " + mechanic.getVarioConditionValueQuantity().intValue() + ", save " + mechanic.getVarioDiscountPercentage() + "% ";
//                     }
//                  }
//               }
//
//               if (mechanic.getVarioConditionType() == Turnover) {
//                  if (mechanic.getHasGiftOutsideAssortment()) {
//                     for (int i = 0; i < currencyList.size(); i++) {
//                        currency = currencyList.get(i);
//                        description += "Buy a total of " + mechanic.getVarioConditionValueTurnover().get(currency) + " " + currency + " items from the list ";
//                     }
//
//                  } else {
//                     if (mechanic.getVarioDiscountUnit() == Value) {
//                        for (int i = 0; i < currencyList.size(); i++) {
//                           currency = currencyList.get(i);
//                           description += "Every " + mechanic.getVarioConditionValueTurnover().get(currency) + " " + currency + ", save " + mechanic.getVarioDiscountValue().get(currency) + " " + currency + " ";
//                        }
//                     }
//                     if (mechanic.getVarioDiscountUnit() == Percentage) {
//                        for (int i = 0; i < currencyList.size(); i++) {
//                           currency = currencyList.get(i);
//                           description += "Every " + mechanic.getVarioConditionValueTurnover().get(currency) + " " + currency + ", save " + mechanic.getVarioDiscountPercentage() + "% Off.";
//                        }
//                     }
//                  }
//               }
//               break;
//
//            case MULTI_UNIT_POOL_COMBINATION:
//               for (String cur : currencyList) {
//                  BigDecimal discountValueDP = Optional.ofNullable(mechanic.getPoolDiscountValueDP().getOrDefault(cur, BigDecimal.ZERO)).orElse(BigDecimal.ZERO);
//                  BigDecimal discountValueDF = Optional.ofNullable(mechanic.getPoolDiscountValueDF().getOrDefault(cur, BigDecimal.ZERO)).orElse(BigDecimal.ZERO);
//
//                  if (discountValueDF.compareTo(BigDecimal.ZERO) == 0 && discountValueDP.compareTo(BigDecimal.ZERO) == 0) {
//                     continue;
//                  }
//
//                  if (mechanic.getPoolConditionValueQuantity() == null) {
//                     continue;
//                  }
//
//                  description += mechanic.getPoolConditionValueQuantity().intValue() + " for ";
//                  if (discountValueDF.compareTo(BigDecimal.ZERO) == 1 && discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                     description += discountValueDF + " " + cur + " DF  or " + discountValueDP + " " + cur + " DP ";
//                  } else if (discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                     description += discountValueDP + " " + cur + " DP ";
//                  } else if (discountValueDF.compareTo(BigDecimal.ZERO) == 1) {
//                     description += discountValueDF + " " + cur + " DF ";
//                  }
//               }
//               break;
//
//            case MULTI_UNIT_LAYERED_COMBINATION:
//               description = "Multi layer promotion: ";
//               if (mechanic.getLayerType() == Quantity_Percentage) {
//                  for (PercentageLayerDTO layer : mechanic.getPercentageLayers()) {
//                     if (layer.getQuantity() != null) {
//                        description += "Buy any " + layer.getQuantity().intValue() + " Items, save " + layer.getPercentage() + "% Off on each item. ";
//                     }
//                  }
//               }
//
//               if (mechanic.getLayerType() == Quantity_Value) {
//                  for (String cur : currencyList) {
//                     for (ValueLayerDTO layer : mechanic.getValueLayers()) {
//                        int layerIndex = layer.getLayerNo() - 1;
//
//                        BigDecimal discountValueDP = BigDecimal.ZERO;
//                        BigDecimal discountValueDF = BigDecimal.ZERO;
//
//                        if (layerIndex < mechanic.getValueLayers().size()) {
//                           discountValueDP = Optional.ofNullable(mechanic.getValueLayers().get(layerIndex).getValues()).map(v -> v.get(cur)).map(ValueLayerDetailsDTO::getPriceDP).orElse(BigDecimal.ZERO);
//                           discountValueDF = Optional.ofNullable(mechanic.getValueLayers().get(layerIndex).getValues()).map(v -> v.get(cur)).map(ValueLayerDetailsDTO::getPriceDF).orElse(BigDecimal.ZERO);
//                        }
//
//                        if (discountValueDF.compareTo(BigDecimal.ZERO) == 0 && discountValueDP.compareTo(BigDecimal.ZERO) == 0) {
//                           continue;
//                        }
//
//                        description += "Buy any " + mechanic.getValueLayers().get(layerIndex).getQuantity().intValue() + " items for ";
//                        if (discountValueDF.compareTo(BigDecimal.ZERO) == 1 && discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                           description += discountValueDF + " " + cur + " DutyFree  and " + discountValueDP + " " + cur + " DutyPaid. ";
//                        } else if (discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                           description += discountValueDP + " " + cur + " DutyPaid. ";
//                        } else if (discountValueDF.compareTo(BigDecimal.ZERO) == 1) {
//                           description += discountValueDF + " " + cur + " DutyFree. ";
//                        }
//                     }
//                  }
//               }
//               break;
//
//            case SPEND_PROMOTION_DISCOUNT:
//               if (mechanic.getSpendDiscountUnit() == Percentage && mechanic.getSpendDiscountPercentage() != null) {
//                  description = "Save " + mechanic.getSpendDiscountPercentage().setScale(0,BigDecimal.ROUND_DOWN)  + "% with minimum spend.";
//               }
//               break;
//
//            case SPEND_PROMOTION_AMOUNT_OFF:
//               if (mechanic.getSpendDiscountUnit() == Value && mechanic.getSpendDiscountValue() != null) {
//                  for (int i = 0; i < currencyList.size(); i++) {
//                     currency = currencyList.get(i);
//                     BigDecimal value = mechanic.getSpendDiscountValue().get(currency);
//                     if(value != null) {
//                        description += "Save " + value.setScale(0, BigDecimal.ROUND_DOWN) + " " + currency + " DP " + value.setScale(0, BigDecimal.ROUND_DOWN) + " " + currency + " DF minimum spend.";
//                     }
//                  }
//               }
//               break;
//
//            case SPEND_PROMOTION_OFFER_ITEM_SPECIAL_PRICE:
//               description = "Special price item minimum spend.";
//               break;
//
//            case SPEND_PROMOTION_OFFER_ITEM_DISCOUNT:
//               if (mechanic.getSpendDiscountUnit() == Percentage && mechanic.getSpendDiscountPercentage() != null) {
//                  description = "Save " + mechanic.getSpendDiscountPercentage().setScale(0,BigDecimal.ROUND_DOWN)  + "% offer item minimum spend.";
//               }
//               break;
//
//            default:
//               description = "No description";
//         }
//
//         if (mechanic.getHasGiftInAssortment() && description != null) {
//            description += GIFT_IN_ASSORTMENT_DESCRIPTION;
//
//         } else if (mechanic.getHasGiftOutsideAssortment() && description != null) {
//            description += GIFT_OUTSIDE_ASSORTMENT_DESCRIPTION;
//         }
//      }
//
//      return description.replace("  ", " ").trim();
//   }
//}
