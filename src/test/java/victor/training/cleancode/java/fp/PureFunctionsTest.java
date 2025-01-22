package victor.training.cleancode.java.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.java.fp.support.*;

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
//    Product p1 = new Product().setId(1L).setCategory(ProductCategory.HOME);
//    Product p2 = new Product().setId(2L).setCategory(ProductCategory.KIDS);
//    when(productRepo.findAllById(List.of(1L, 2L))).thenReturn(List.of(p1, p2));
    when(thirdPartyPrices.fetchPrice(2L)).thenReturn(5d);

    Map<Long, Double> result = priceService.computePrices(13L, List.of(1L, 2L), Map.of(1L, 10d));

    verify(couponRepo).markUsedCoupons(eq(13L), couponCaptor.capture());
    assertThat(couponCaptor.getValue()).containsExactly(coupon1);

    assertThat(result)
        .containsEntry(1L, 8d)
        .containsEntry(2L, 5d);
  }

}