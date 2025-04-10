package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.fp.PureFunction.ApplyCouponsResults;
import victor.training.cleancode.fp.support.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PureFunctionsTest {
  public static final long PRODUCT_ID = 1L;
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
    Product p1 = new Product().id(PRODUCT_ID).category(ProductCategory.HOME);
    Product p2 = new Product().id(2L).category(ProductCategory.KIDS);
    when(productRepo.findAllById(List.of(PRODUCT_ID, 2L))).thenReturn(List.of(p1, p2));
    when(thirdPartyPrices.fetchPrice(2L)).thenReturn(5d);

    Map<Long, Double> result = priceService.computePrices(13L, List.of(PRODUCT_ID, 2L), Map.of(PRODUCT_ID, 10d));

    verify(couponRepo).markUsedCoupons(eq(13L), couponCaptor.capture());
    assertThat(couponCaptor.getValue()).containsExactly(coupon1);

    assertThat(result)
        .containsEntry(PRODUCT_ID, 8d)
        .containsEntry(2L, 5d);
  }

  @Test
  void computePricesUsesInternalPricesWhenAvailable() {
    Product product = new Product().id(PRODUCT_ID).category(ProductCategory.HOME);

    ApplyCouponsResults result = PureFunction.applyCoupons(
        List.of(product),
        Map.of(PRODUCT_ID, 20d),
        List.of());

    assertThat(result.finalPrices()).containsEntry(PRODUCT_ID, 20d);
    assertThat(result.usedCoupons()).isEmpty();
  }

  @Test
  void computePricesFetchesThirdPartyPriceWhenInternalPriceMissing() {
    Product product = new Product().id(PRODUCT_ID).category(ProductCategory.HOME);
    when(customerRepo.findById(13L)).thenReturn(new Customer(List.of()));
    when(productRepo.findAllById(List.of(PRODUCT_ID))).thenReturn(List.of(product));
    when(thirdPartyPrices.fetchPrice(PRODUCT_ID)).thenReturn(15d);

    Map<Long, Double> result = priceService.computePrices(13L, List.of(PRODUCT_ID), Map.of());

    assertThat(result).containsEntry(PRODUCT_ID, 15d);
  }

  @Test
  void computePricesAppliesApplicableCoupons() {
    Coupon coupon = new Coupon(ProductCategory.HOME, 5);
    Customer customer = new Customer(List.of(coupon));
    Product product = new Product().id(PRODUCT_ID).category(ProductCategory.HOME);
    when(customerRepo.findById(13L)).thenReturn(customer);
    when(productRepo.findAllById(List.of(PRODUCT_ID))).thenReturn(List.of(product));

    Map<Long, Double> result = priceService.computePrices(13L, List.of(PRODUCT_ID), Map.of(PRODUCT_ID, 20d));

    assertThat(result).containsEntry(PRODUCT_ID, 15d);
  }

  @Test
  void computePricesDoesNotApplyInapplicableCoupons() {
    Coupon coupon = new Coupon(ProductCategory.ELECTRONICS, 5);
    Customer customer = new Customer(List.of(coupon));
    Product product = new Product().id(PRODUCT_ID).category(ProductCategory.HOME);
    when(customerRepo.findById(13L)).thenReturn(customer);
    when(productRepo.findAllById(List.of(PRODUCT_ID))).thenReturn(List.of(product));

    Map<Long, Double> result = priceService.computePrices(13L, List.of(PRODUCT_ID), Map.of(PRODUCT_ID, 20d));

    assertThat(result).containsEntry(PRODUCT_ID, 20d);
  }

  @Test
  void computePricesMarksUsedCoupons() {
    Coupon coupon = new Coupon(ProductCategory.HOME, 5);
    Product product = new Product().id(PRODUCT_ID).category(ProductCategory.HOME);
    when(customerRepo.findById(13L)).thenReturn(new Customer(List.of(coupon)));
    when(productRepo.findAllById(List.of(PRODUCT_ID))).thenReturn(List.of(product));

    priceService.computePrices(13L, List.of(PRODUCT_ID), Map.of(PRODUCT_ID, 20d));

    verify(couponRepo).markUsedCoupons(eq(13L), couponCaptor.capture());
    assertThat(couponCaptor.getValue()).containsExactly(coupon);
  }

}