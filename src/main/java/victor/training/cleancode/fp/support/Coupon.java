package victor.training.cleancode.fp.support;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public
class Coupon {
  private final ProductCategory category;
  private final int discountAmount;
  private boolean autoApply = true;

  public Coupon(ProductCategory category, int discountAmount) {
    this.category = category;
    this.discountAmount = discountAmount;
  }

  public boolean autoApply() {
    return autoApply;
  }

  public boolean isApplicableFor(Product product) {
    //*

    System.out.println("ceva");

    /*/

        System.out.println("altceva");

    //*/


//    product.setCategory(ProductCategory.ME).setName("aa")
    return (product.getCategory() == category || category == null) && !product.isPremium();
  }

  public Double apply(Product product, Double price) {
    if (!isApplicableFor(product)) {
      throw new IllegalArgumentException();
    }
    return price - discountAmount;
  }
}
