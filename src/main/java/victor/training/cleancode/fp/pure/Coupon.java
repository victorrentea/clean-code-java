package victor.training.cleancode.fp.pure;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
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

  public void setAutoApply(boolean autoApply) {
    this.autoApply = autoApply;
  }

  // Cate logica pun in Model?
  // R: atata cat sa n-ai nevoie vreodata s-o mockuiesti -> adica nu ff multa: 10 ifuri
  // antipattern: sa mockuiest metode de pe Domain Model (exceptie: cand faci DDD Aggregates)
  public boolean isApplicableFor(Product product) {
    return (product.getCategory() == category || category == null) && !product.isPremium();
  }

  public Double apply(Product product, Double price) {
    if (!isApplicableFor(product)) {
      throw new IllegalArgumentException();
    }
    return price - discountAmount;
  }
}
