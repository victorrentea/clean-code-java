package victor.training.mindit;

public enum MechanicType {
SURPRISE,

   DISCOUNT,//(new DiscountMechanic()),
   PROMOTION_PRICE, MULTI_UNIT_SIMQTY_LOWEST_PRICE, MULTI_UNIT_SIMQTY_HIGHEST_PRICE, MULTI_UNIT_FIXED_COMBINATION, MULTI_UNIT_VARIO_COMBINATION, MULTI_UNIT_POOL_COMBINATION, MULTI_UNIT_LAYERED_COMBINATION, MULTI_UNIT_PACK_AND_COMBO,



   NON_VALUE_NO_DISCOUNT,//(new NonValuePresenter()),
   NON_VALUE_SMALL,//(new NonValuePresenter()),
   NON_VALUE_MEDIUM,//(new NonValuePresenter()),
   NON_VALUE_LARGE,//(new NonValuePresenter()),


   MULTI_UNIT_SIMQTY_EVERY_ITEM, SPEND_PROMOTION_DISCOUNT, SPEND_PROMOTION_AMOUNT_OFF, SPEND_PROMOTION_OFFER_ITEM_SPECIAL_PRICE, SPEND_PROMOTION_OFFER_ITEM_DISCOUNT;
//private final MechanicTypePresenter presenter;
//
//   MechanicType(MechanicTypePresenter presenter) {
//      this.presenter = presenter;
//   }
//
//   public MechanicTypePresenter getPresenter() {
//      return presenter;
//   }
//
//   public String getDescription() {
//      return null;
//   }
}
