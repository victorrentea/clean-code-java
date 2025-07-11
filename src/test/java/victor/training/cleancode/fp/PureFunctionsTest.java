package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.fp.support.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PureFunctionsTest {
  @Mock
  CustomerRepo customerRepo;
  @Mock
  ThirdPartyPricesApi thirdPartyPrices;
  @Mock
  CouponRepo couponRepo;
  @Mock
  ProductRepo productRepo;
  @InjectMocks
  PureFunction priceService;
  @Captor
  ArgumentCaptor<List<Coupon>> couponCaptor;

  @Test
  void computePrices() {
    Coupon coupon1 = new Coupon(ProductCategory.HOME, 2);
    Coupon coupon2 = new Coupon(ProductCategory.ELECTRONICS, 4);
    Customer customer = new Customer(List.of(coupon1, coupon2));
    when(customerRepo.findById(13L)).thenReturn(customer);
    Product p1 = new Product().setId(1L).setCategory(ProductCategory.HOME);
    Product p2 = new Product().setId(2L).setCategory(ProductCategory.KIDS);
    when(productRepo.findAllById(List.of(1L, 2L))).thenReturn(List.of(p1, p2));
    when(thirdPartyPrices.fetchPrice(2L)).thenReturn(5d);

    Map<Long, Double> result = priceService.computePrices(13L, List.of(1L, 2L), Map.of(1L, 10d));

    verify(couponRepo).markUsedCoupons(eq(13L), couponCaptor.capture());
    assertThat(couponCaptor.getValue()).containsExactly(coupon1);

    assertThat(result)
        .containsEntry(1L, 8d)
        .containsEntry(2L, 5d);
  }

  @Test
  void applyCoupons_noCoupons() {
    // Arrange
    List<Product> products = List.of(
        new Product().setId(1L).setCategory(ProductCategory.HOME),
        new Product().setId(2L).setCategory(ProductCategory.KIDS)
    );
    Map<Long, Double> prices = Map.of(1L, 10.0, 2L, 20.0);
    Customer customer = new Customer(List.of());

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices())
        .containsEntry(1L, 10.0)
        .containsEntry(2L, 20.0);
  }

  @Test
  void applyCoupons_nonApplicableCoupons() {
    // Arrange
    List<Product> products = List.of(
        new Product().setId(1L).setCategory(ProductCategory.HOME),
        new Product().setId(2L).setCategory(ProductCategory.KIDS)
    );
    Map<Long, Double> prices = Map.of(1L, 10.0, 2L, 20.0);

    // Coupons for categories that don't match the products
    Coupon coupon1 = new Coupon(ProductCategory.ELECTRONICS, 5);
    Coupon coupon2 = new Coupon(ProductCategory.ME, 3);
    Customer customer = new Customer(List.of(coupon1, coupon2));

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices())
        .containsEntry(1L, 10.0)
        .containsEntry(2L, 20.0);
  }

  @Test
  void applyCoupons_applicableCoupons() {
    // Arrange
    Product homeProduct = new Product().setId(1L).setCategory(ProductCategory.HOME);
    Product kidsProduct = new Product().setId(2L).setCategory(ProductCategory.KIDS);
    List<Product> products = List.of(homeProduct, kidsProduct);

    Map<Long, Double> prices = new HashMap<>();
    prices.put(1L, 10.0);
    prices.put(2L, 20.0);

    Coupon homeCoupon = new Coupon(ProductCategory.HOME, 2);
    Coupon kidsCoupon = new Coupon(ProductCategory.KIDS, 5);
    Customer customer = new Customer(List.of(homeCoupon, kidsCoupon));

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    assertThat(result.usedCoupons()).containsExactlyInAnyOrder(homeCoupon, kidsCoupon);
    assertThat(result.finalPrices())
        .containsEntry(1L, 8.0)  // 10 - 2
        .containsEntry(2L, 15.0); // 20 - 5
  }

  @Test
  void applyCoupons_premiumProductsNotEligible() {
    // Arrange
    Product regularProduct = new Product().setId(1L).setCategory(ProductCategory.HOME);
    Product premiumProduct = new Product().setId(2L).setCategory(ProductCategory.HOME).setPremium(true);
    List<Product> products = List.of(regularProduct, premiumProduct);

    Map<Long, Double> prices = Map.of(1L, 10.0, 2L, 20.0);

    Coupon homeCoupon = new Coupon(ProductCategory.HOME, 2);
    Customer customer = new Customer(List.of(homeCoupon));

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    assertThat(result.usedCoupons()).containsExactly(homeCoupon);
    assertThat(result.finalPrices())
        .containsEntry(1L, 8.0)  // 10 - 2
        .containsEntry(2L, 20.0); // Premium product, coupon not applied
  }

  @Test
  void applyCoupons_nonAutoApplyCoupons() {
    // Arrange
    Product homeProduct = new Product().setId(1L).setCategory(ProductCategory.HOME);
    List<Product> products = List.of(homeProduct);

    Map<Long, Double> prices = Map.of(1L, 10.0);

    Coupon homeCoupon = new Coupon(ProductCategory.HOME, 2);
    homeCoupon.setAutoApply(false); // Set autoApply to false
    Customer customer = new Customer(List.of(homeCoupon));

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    assertThat(result.usedCoupons()).isEmpty(); // Coupon not used because autoApply is false
    assertThat(result.finalPrices())
        .containsEntry(1L, 10.0); // Original price
  }

  @Test
  void applyCoupons_multipleCouponsForSameCategory() {
    // Arrange
    Product homeProduct = new Product().setId(1L).setCategory(ProductCategory.HOME);
    List<Product> products = List.of(homeProduct);

    Map<Long, Double> prices = Map.of(1L, 10.0);

    // Two coupons for the same category
    Coupon homeCoupon1 = new Coupon(ProductCategory.HOME, 2);
    Coupon homeCoupon2 = new Coupon(ProductCategory.HOME, 3);
    Customer customer = new Customer(List.of(homeCoupon1, homeCoupon2));

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    // Both coupons should be used
    assertThat(result.usedCoupons()).containsExactly(homeCoupon1, homeCoupon2);
    assertThat(result.finalPrices())
        .containsEntry(1L, 5.0); // 10 - 2 - 3
  }

  @Test
  void applyCoupons_nullCategoryAppliesForAll() {
    // Arrange
    Product homeProduct = new Product().setId(1L).setCategory(ProductCategory.HOME);
    Product kidsProduct = new Product().setId(2L).setCategory(ProductCategory.KIDS);
    List<Product> products = List.of(homeProduct, kidsProduct);

    Map<Long, Double> prices = Map.of(1L, 10.0, 2L, 20.0);

    // Coupon with null category applies to all categories
    Coupon universalCoupon = new Coupon(null, 5);
    Customer customer = new Customer(List.of(universalCoupon));

    // Act
    var result = PureFunction.applyCoupons(products, prices, customer);

    // Assert
    // The universal coupon should be used only once
    assertThat(result.usedCoupons()).containsExactly(universalCoupon);
    assertThat(result.finalPrices())
        .containsEntry(1L, 5.0)  // 10 - 5
        .containsEntry(2L, 20.0); // Original price, coupon already used
  }

}
