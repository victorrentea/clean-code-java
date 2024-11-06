package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.fp.PureFunction.CouponApplicationResult;
import victor.training.cleancode.fp.support.*;

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
  void appliesCouponsCorrectly() {
    List<Product> products = List.of(
        new Product().setId(1L).setCategory(ProductCategory.HOME),
        new Product().setId(2L).setCategory(ProductCategory.ELECTRONICS)
    );
    Map<Long, Double> initialPrices = Map.of(1L, 100.0, 2L, 200.0);
    List<Coupon> coupons = List.of(
        new Coupon(ProductCategory.HOME, 10),
        new Coupon(ProductCategory.ELECTRONICS, 20)
    );

    CouponApplicationResult result = PureFunction.applyCoupons(products, initialPrices, coupons);

    assertThat(result.usedCoupons()).containsExactlyInAnyOrder(coupons.get(0), coupons.get(1));
    assertThat(result.finalPrices()).containsEntry(1L, 90.0).containsEntry(2L, 180.0);
  }

  @Test
  void noCouponsAppliedWhenNoneAreApplicable() {
    List<Product> products = List.of(
        new Product().setId(1L).setCategory(ProductCategory.HOME),
        new Product().setId(2L).setCategory(ProductCategory.ELECTRONICS)
    );
    Map<Long, Double> initialPrices = Map.of(1L, 100.0, 2L, 200.0);
    List<Coupon> coupons = List.of(
        new Coupon(ProductCategory.KIDS, 10)
    );

    CouponApplicationResult result = PureFunction.applyCoupons(products, initialPrices, coupons);

    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices()).containsEntry(1L, 100.0).containsEntry(2L, 200.0);
  }

  @Test
  void appliesOnlyOneCouponPerProduct() {
    List<Product> products = List.of(
        new Product().setId(1L).setCategory(ProductCategory.HOME)
    );
    Map<Long, Double> initialPrices = Map.of(1L, 100.0);
    List<Coupon> coupons = List.of(
        new Coupon(ProductCategory.HOME, 10),
        new Coupon(ProductCategory.HOME, 20)
    );

    CouponApplicationResult result = PureFunction.applyCoupons(products, initialPrices, coupons);

    assertThat(result.usedCoupons()).containsExactly(coupons.get(0));
    assertThat(result.finalPrices()).containsEntry(1L, 90.0);
  }

  @Test
  void handlesEmptyProductList() {
    List<Product> products = List.of();
    Map<Long, Double> initialPrices = Map.of();
    List<Coupon> coupons = List.of(
        new Coupon(ProductCategory.HOME, 10)
    );

    CouponApplicationResult result = PureFunction.applyCoupons(products, initialPrices, coupons);

    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices()).isEmpty();
  }

  @Test
  void handlesEmptyCouponList() {
    List<Product> products = List.of(
        new Product().setId(1L).setCategory(ProductCategory.HOME)
    );
    Map<Long, Double> initialPrices = Map.of(1L, 100.0);
    List<Coupon> coupons = List.of();

    CouponApplicationResult result = PureFunction.applyCoupons(products, initialPrices, coupons);

    assertThat(result.usedCoupons()).isEmpty();
    assertThat(result.finalPrices()).containsEntry(1L, 100.0);
  }

}