package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.fp.support.Coupon;
import victor.training.cleancode.fp.support.Product;
import victor.training.cleancode.fp.support.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApplyCouponsTest {

  private Product product(long id, ProductCategory category) {
    Product p = new Product("P" + id, category);
    p.setId(id);
    return p;
  }

  @Test
  void noCoupons_keepsPricesAndNoUsedCoupons() {
    List<Product> products = List.of(product(1, ProductCategory.ELECTRONICS));
    Map<Long, Double> prices = Map.of(1L, 100.0);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(products, prices, List.of());

    assertThat(res.finalPrices()).containsEntry(1L, 100.0);
    assertThat(res.usedCoupons()).isEmpty();
  }

  @Test
  void applicableCoupon_appliesOnceAndIsMarkedUsed() {
    Product prod = product(1, ProductCategory.ELECTRONICS);
    Map<Long, Double> prices = Map.of(1L, 100.0);
    Coupon coupon = new Coupon(ProductCategory.ELECTRONICS, 10);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(List.of(prod), prices, List.of(coupon));

    assertThat(res.finalPrices()).containsEntry(1L, 90.0);
    assertThat(res.usedCoupons()).containsExactly(coupon);
  }

  @Test
  void nonApplicableCoupon_dueToCategory_keepsPrice() {
    Product prod = product(1, ProductCategory.HOME);
    Map<Long, Double> prices = Map.of(1L, 50.0);
    Coupon coupon = new Coupon(ProductCategory.ELECTRONICS, 5);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(List.of(prod), prices, List.of(coupon));

    assertThat(res.finalPrices()).containsEntry(1L, 50.0);
    assertThat(res.usedCoupons()).isEmpty();
  }

  @Test
  void premiumProduct_ignoresCoupons() {
    Product prod = product(1, ProductCategory.ELECTRONICS);
    prod.setPremium(true);
    Map<Long, Double> prices = Map.of(1L, 200.0);
    Coupon coupon = new Coupon(ProductCategory.ELECTRONICS, 20);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(List.of(prod), prices, List.of(coupon));

    assertThat(res.finalPrices()).containsEntry(1L, 200.0);
    assertThat(res.usedCoupons()).isEmpty();
  }

  @Test
  void sameCoupon_isUsedAtMostOnceAcrossProducts() {
    Product p1 = product(1, ProductCategory.ELECTRONICS);
    Product p2 = product(2, ProductCategory.ELECTRONICS);
    Map<Long, Double> prices = new HashMap<>();
    prices.put(1L, 100.0);
    prices.put(2L, 200.0);

    Coupon coupon = new Coupon(ProductCategory.ELECTRONICS, 5);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(List.of(p1, p2), prices, List.of(coupon));

    // Only the first product in iteration gets discounted; second stays the same
    assertThat(res.finalPrices()).containsEntry(1L, 95.0).containsEntry(2L, 200.0);
    assertThat(res.usedCoupons()).containsExactly(coupon);
  }

  @Test
  void multipleCoupons_chainApplicationInOrder() {
    Product prod = product(1, ProductCategory.ELECTRONICS);
    Map<Long, Double> prices = Map.of(1L, 100.0);

    Coupon c1 = new Coupon(ProductCategory.ELECTRONICS, 10); // 100 -> 90
    Coupon c2 = new Coupon(null, 5); // applicable to non-premium any category -> 90 -> 85

    List<Coupon> coupons = new ArrayList<>();
    coupons.add(c1);
    coupons.add(c2);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(List.of(prod), prices, coupons);

    assertThat(res.finalPrices()).containsEntry(1L, 85.0);
    assertThat(res.usedCoupons()).containsExactly(c1, c2);
  }

  @Test
  void couponWithAutoApplyFalse_isIgnored() {
    Product prod = product(1, ProductCategory.ELECTRONICS);
    Map<Long, Double> prices = Map.of(1L, 100.0);
    Coupon coupon = new Coupon(ProductCategory.ELECTRONICS, 10);
    coupon.setAutoApply(false);

    PureFunction pf = new PureFunction(null, null, null, null);
    PureFunction.ApplyCouponsResult res = pf.applyCoupons(List.of(prod), prices, List.of(coupon));

    assertThat(res.finalPrices()).containsEntry(1L, 100.0);
    assertThat(res.usedCoupons()).isEmpty();
  }
}
