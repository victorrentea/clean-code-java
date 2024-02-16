package victor.training.cleancode.fp.support;

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

  public void setAutoApply(boolean autoApply) {
    this.autoApply = autoApply;
  }

  public boolean isApplicableFor(Product product) {
    return (product.getCategory() == category || category == null) && !product.isPremium();
  }

  public Double apply(Product product, Double price) {
    if (!isApplicableFor(product)) {
      throw new IllegalArgumentException();
    }
    return price - discountAmount;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Coupon)) return false;
    final Coupon other = (Coupon) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$category = this.category;
    final Object other$category = other.category;
    if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
    if (this.discountAmount != other.discountAmount) return false;
    if (this.autoApply != other.autoApply) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Coupon;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $category = this.category;
    result = result * PRIME + ($category == null ? 43 : $category.hashCode());
    result = result * PRIME + this.discountAmount;
    result = result * PRIME + (this.autoApply ? 79 : 97);
    return result;
  }
}
