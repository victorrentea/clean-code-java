package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.fp.support.Coupon;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductCategory;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApplyCouponsTest {

  @Test
  void coupon_autoApply_false_is_not_applied() {
    Coupon coupon = new Coupon(ProductCategory.HOME, 3);
    coupon.setAutoApply(false);
    Product p = new Product().setId(10L).setCategory(ProductCategory.HOME);

    PureFunction.ApplyCouponsResult result = PureFunction.applyCoupons(
        List.of(p),
        Map.of(10L, 20d),
        List.of(coupon));

    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices()).containsEntry(10L, 20d);
  }

  @Test
  void coupon_applicable_only_once_across_products() {
    Coupon coupon = new Coupon(ProductCategory.HOME, 5);

    Product p1 = new Product().setId(11L).setCategory(ProductCategory.HOME);
    Product p2 = new Product().setId(12L).setCategory(ProductCategory.HOME);

    PureFunction.ApplyCouponsResult result = PureFunction.applyCoupons(
        List.of(p1, p2),
        Map.of(11L, 30d, 12L, 40d),
        List.of(coupon));

    assertThat(result.usedCoupons()).containsExactly(coupon);
    // coupon applied to first product only (iteration order of input list)
    assertThat(result.finalPrices()).containsEntry(11L, 25d);
    assertThat(result.finalPrices()).containsEntry(12L, 40d);
  }

  @Test
  void multiple_coupons_stack_in_order() {
    Coupon c1 = new Coupon(ProductCategory.ELECTRONICS, 2);
    Coupon c2 = new Coupon(ProductCategory.ELECTRONICS, 3);

    Product p = new Product().setId(20L).setCategory(ProductCategory.ELECTRONICS);

    PureFunction.ApplyCouponsResult result = PureFunction.applyCoupons(
        List.of(p),
        Map.of(20L, 15d),
        List.of(c1, c2));

    assertThat(result.usedCoupons()).containsExactly(c1, c2);
    // 15 - 2 - 3
    assertThat(result.finalPrices()).containsEntry(20L, 10d);
  }

  @Test
  void premium_products_are_not_applicable() {
    Coupon coupon = new Coupon(ProductCategory.KIDS, 4);

    Product p = new Product().setId(70L).setCategory(ProductCategory.KIDS).setPremium(true);

    PureFunction.ApplyCouponsResult result = PureFunction.applyCoupons(
        List.of(p),
        Map.of(70L, 50d),
        List.of(coupon));

    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices()).containsEntry(70L, 50d);
  }

  @Test
  void empty_coupons_list_keeps_prices() {
    Product p = new Product().setId(90L).setCategory(ProductCategory.HOME);

    PureFunction.ApplyCouponsResult result = PureFunction.applyCoupons(
        List.of(p),
        Map.of(90L, 12d),
        List.of());

    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices()).containsEntry(90L, 12d);
  }
}
