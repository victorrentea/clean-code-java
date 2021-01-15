//package victor.training.mindit.Sample3;
//
//import victor.training.mindit.CommunicationView;
//import victor.training.mindit.MechanicDTO;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static victor.training.mindit.ConditionType.Quantity;
//import static victor.training.mindit.ConditionType.Turnover;
//import static victor.training.mindit.DiscountUnit.Percentage;
//import static victor.training.mindit.DiscountUnit.Value;
//import static victor.training.mindit.LayerType.Quantity_Percentage;
//import static victor.training.mindit.LayerType.Quantity_Value;
//import static victor.training.mindit.Sample1.computeDiscountCommunication;
//
//
//public class Sample3 {
//
//   public static final String GIFT_OUTSIDE_ASSORTMENT_DESCRIPTION = "";
//
//   //moved from FE side
//   public static String computeDescription(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange) {
//
////      if (mechanic.getVarioConditionValueTurnover() == null) {
////         mechanic.setVarioConditionValueTurnover(new HashMap<>());
////      }
////
////      if (mechanic.getVarioDiscountValue() == null) {
////         mechanic.setVarioDiscountValue(new HashMap<>());
////      }
//
//
//      //moved from FE
//      if (mechanic.getSelectedMechanicType() == null) {
//         return "";
//      }
//      String description = zaSwitch(mechanic, currencyList, bulkChange);
//
//      if (mechanic.getHasGiftInAssortment() && description != null) {
//         description += "Gift in assortiment";
//
//      } else if (mechanic.getHasGiftOutsideAssortment() && description != null) {
//         description += GIFT_OUTSIDE_ASSORTMENT_DESCRIPTION;
//      }
//
//      return description.replaceAll("\\s+", " ").trim();
//
//   }
//
//   public interface MechanicTypePresenter {
//      String computeDescription(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange);
//      String computeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList) ;
//   }
//
//   public static class DiscountMechanic implements MechanicTypePresenter {
//      public String computeDescription(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange) {
//         return "Save " + mechanic.getPercentageOff() + "% ";
//      }
//      public String computeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList) {
//         CommunicationView dto =
//
//             computeDiscountCommunication(percentage, displayedPercentage);
//         return dto.asFormattedMessage();
//
//      }
//   }
//   public static class PromotionPriceMechanic implements MechanicTypePresenter {
//      public String computeDescription(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange) {
//         return "Save " + mechanic.getPercentageOff() + "% ";
//      }
//      public String computeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList) {
//         CommunicationView dto =
//
//             computeDiscountCommunication(percentage, displayedPercentage);
//         return dto.asFormattedMessage();
//
//      }
//   }
//   public static class NonValuePresenter implements MechanicTypePresenter {
//      public String computeDescription(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange) {
//         return "Save " + mechanic.getPercentageOff() + "% ";
//      }
//      public String computeMarketingCommunication(MechanicDTO mechanic, List<String> currencyList) {
//         CommunicationView dto =
//
//             computeDiscountCommunication(percentage, displayedPercentage);
//         return dto.asFormattedMessage();
//
//      }
//   }
//
//   private static String zaSwitch(MechanicDTO mechanic, List<String> currencyList, boolean bulkChange) {
//      String description = "";
//      switch (mechanic.getSelectedMechanicType()) {
//         case DISCOUNT:
//            return "Save " + mechanic.getPercentageOff() + "% ";
//
//         case PROMOTION_PRICE:
//            if (bulkChange) {
//               return "Fixed Promo Price";
//            } else {
//               return "Special Price";
//            }
//
//         case MULTI_UNIT_SIMQTY_LOWEST_PRICE:
//            return "Buy all, get lowest price free";
//
//         case MULTI_UNIT_SIMQTY_HIGHEST_PRICE:
//            return "Buy all items and get the highest price for free ";
//
//         case MULTI_UNIT_SIMQTY_EVERY_ITEM:
//            BigDecimal noItem = mechanic.getNoItems();
//            if (noItem == null || mechanic.getPercentageOff() == null) {
//               return description;
//            } else if (new BigDecimal(100).compareTo(mechanic.getPercentageOff().get()) == 0 && noItem != null) {
//               return "Buy " + (noItem.intValue() - 1) + " items, get 1 (extra item) for Free ";
//            } else {
//               return "Every " + noItem.intValue() + ", " + mechanic.getPercentageOff() + "% Off lowest price ";
//            }
//
//         case MULTI_UNIT_FIXED_COMBINATION:
//            if (mechanic.getHasGiftInAssortment()) {
//               return "Buy all items ";
//            } else {
//               return "Buy all, save " + mechanic.getPercentageOff() + "% ";
//            }
//
//         case NON_VALUE_NO_DISCOUNT:
//         case NON_VALUE_SMALL:
//         case NON_VALUE_MEDIUM:
//         case NON_VALUE_LARGE:
//            if (bulkChange) {
//               return "All offers that are not discounted ";
//            } else {
//               return mechanic.getSelectedMechanicType().getDescription();
//            }
//
//         case MULTI_UNIT_PACK_AND_COMBO:
//            return "Pack and Combo Promotion";
//
//         case MULTI_UNIT_VARIO_COMBINATION:
//            String currency;
//            if (mechanic.getVarioConditionType() == Quantity && mechanic.getVarioConditionValueQuantity() != null) {
//               if (mechanic.getHasGiftOutsideAssortment()) {
//                  description = "Buy any " + mechanic.getVarioConditionValueQuantity().intValue() + " items from the list ";
//               } else {
//                  if (mechanic.getVarioDiscountUnit() == Value) {
//                     for (int i = 0; i < currencyList.size(); i++) {
//                        currency = currencyList.get(i);
//                        description += "Buy any " + mechanic.getVarioConditionValueQuantity().intValue() + ", save " + mechanic.getVarioDiscountValue().get(currency) + " on each ";
//                     }
//                  }
//                  if (mechanic.getVarioDiscountUnit() == Percentage) {
//                     description = "Buy any " + mechanic.getVarioConditionValueQuantity().intValue() + ", save " + mechanic.getVarioDiscountPercentage() + "% ";
//                  }
//               }
//            }
//
//            if (mechanic.getVarioConditionType() == Turnover) {
//               if (mechanic.getHasGiftOutsideAssortment()) {
//                  for (int i = 0; i < currencyList.size(); i++) {
//                     currency = currencyList.get(i);
//                     description += "Buy a total of " + mechanic.getVarioConditionValueTurnover().get(currency) + " " + currency + " items from the list ";
//                  }
//
//               } else {
//                  if (mechanic.getVarioDiscountUnit() == Value) {
//                     for (int i = 0; i < currencyList.size(); i++) {
//                        currency = currencyList.get(i);
//                        description += "Every " + mechanic.getVarioConditionValueTurnover().get(currency) + " " + currency + ", save " + mechanic.getVarioDiscountValue().get(currency) + " " + currency + " ";
//                     }
//                  }
//                  if (mechanic.getVarioDiscountUnit() == Percentage) {
//                     for (int i = 0; i < currencyList.size(); i++) {
//                        currency = currencyList.get(i);
//                        description += "Every " + mechanic.getVarioConditionValueTurnover().get(currency) + " " + currency + ", save " + mechanic.getVarioDiscountPercentage() + "% Off.";
//                     }
//                  }
//               }
//            }
//            return description;
//
//         case MULTI_UNIT_POOL_COMBINATION:
//            for (String cur : currencyList) {
//               BigDecimal discountValueDP = Optional.ofNullable(mechanic.getPoolDiscountValueDP().getOrDefault(cur, BigDecimal.ZERO)).orElse(BigDecimal.ZERO);
//               BigDecimal discountValueDF = Optional.ofNullable(mechanic.getPoolDiscountValueDF().getOrDefault(cur, BigDecimal.ZERO)).orElse(BigDecimal.ZERO);
//
//               if (discountValueDF.compareTo(BigDecimal.ZERO) == 0 && discountValueDP.compareTo(BigDecimal.ZERO) == 0) {
//                  continue;
//               }
//
//               if (mechanic.getPoolConditionValueQuantity() == null) {
//                  continue;
//               }
//
//               description += mechanic.getPoolConditionValueQuantity().intValue() + " for ";
//               if (discountValueDF.compareTo(BigDecimal.ZERO) == 1 && discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                  description += discountValueDF + " " + cur + " DF  or " + discountValueDP + " " + cur + " DP ";
//               } else if (discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                  description += discountValueDP + " " + cur + " DP ";
//               } else if (discountValueDF.compareTo(BigDecimal.ZERO) == 1) {
//                  description += discountValueDF + " " + cur + " DF ";
//               }
//            }
//            return description;
//
//         case MULTI_UNIT_LAYERED_COMBINATION:
//            description = "Multi layer promotion: ";
//            if (mechanic.getLayerType() == Quantity_Percentage) {
//               for (PercentageLayerDTO layer : mechanic.getPercentageLayers()) {
//                  if (layer.getQuantity() != null) {
//                     description += "Buy any " + layer.getQuantity().intValue() + " Items, save " + layer.getPercentage() + "% Off on each item. ";
//                  }
//               }
//            }
//
//            if (mechanic.getLayerType() == Quantity_Value) {
//               for (String cur : currencyList) {
//                  for (ValueLayerDTO layer : mechanic.getValueLayers()) {
//                     int layerIndex = layer.getLayerNo() - 1;
//
//                     BigDecimal discountValueDP = BigDecimal.ZERO;
//                     BigDecimal discountValueDF = BigDecimal.ZERO;
//
//                     if (layerIndex < mechanic.getValueLayers().size()) {
//                        discountValueDP = Optional.ofNullable(mechanic.getValueLayers().get(layerIndex).getValues()).map(v -> v.get(cur)).map(ValueLayerDetailsDTO::getPriceDP).orElse(BigDecimal.ZERO);
//                        discountValueDF = Optional.ofNullable(mechanic.getValueLayers().get(layerIndex).getValues()).map(v -> v.get(cur)).map(ValueLayerDetailsDTO::getPriceDF).orElse(BigDecimal.ZERO);
//                     }
//
//                     if (discountValueDF.compareTo(BigDecimal.ZERO) == 0 && discountValueDP.compareTo(BigDecimal.ZERO) == 0) {
//                        continue;
//                     }
//
//                     description += "Buy any " + mechanic.getValueLayers().get(layerIndex).getQuantity().intValue() + " items for ";
//                     if (discountValueDF.compareTo(BigDecimal.ZERO) == 1 && discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                        description += discountValueDF + " " + cur + " DutyFree  and " + discountValueDP + " " + cur + " DutyPaid. ";
//                     } else if (discountValueDP.compareTo(BigDecimal.ZERO) == 1) {
//                        description += discountValueDP + " " + cur + " DutyPaid. ";
//                     } else if (discountValueDF.compareTo(BigDecimal.ZERO) == 1) {
//                        description += discountValueDF + " " + cur + " DutyFree. ";
//                     }
//                  }
//               }
//            }
//            return description;
//
//         case SPEND_PROMOTION_DISCOUNT:
//            if (mechanic.getSpendDiscountUnit() == Percentage && mechanic.getSpendDiscountPercentage() != null) {
//               return "Save " + mechanic.getSpendDiscountPercentage().setScale(0, BigDecimal.ROUND_DOWN) + "% with minimum spend.";
//            }
//            return description;
//
//         case SPEND_PROMOTION_AMOUNT_OFF:
//            if (mechanic.getSpendDiscountUnit() == Value && mechanic.getSpendDiscountValue() != null) {
//               for (int i = 0; i < currencyList.size(); i++) {
//                  currency = currencyList.get(i);
//                  BigDecimal value = mechanic.getSpendDiscountValue().get(currency);
//                  if (value != null) {
//                     description += "Save " + value.setScale(0, BigDecimal.ROUND_DOWN) + " " + currency + " DP " + value.setScale(0, BigDecimal.ROUND_DOWN) + " " + currency + " DF minimum spend.";
//                  }
//               }
//            }
//            return description;
//
//         case SPEND_PROMOTION_OFFER_ITEM_SPECIAL_PRICE: return "Special price item minimum spend.";
//         case SPEND_PROMOTION_OFFER_ITEM_DISCOUNT:
//            if (mechanic.getSpendDiscountUnit() == Percentage && mechanic.getSpendDiscountPercentage() != null) {
//               return "Save " + mechanic.getSpendDiscountPercentage().setScale(0, BigDecimal.ROUND_DOWN) + "% offer item minimum spend.";
//            }
//            return "";
//         default:
//            return "No description";
//      }
//   }
//}
