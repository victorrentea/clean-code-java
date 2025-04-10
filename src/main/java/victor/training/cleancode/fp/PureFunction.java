package victor.training.cleancode.fp;

import com.google.common.annotations.VisibleForTesting;
import victor.training.cleancode.fp.support.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PureFunction {
  private final CustomerRepo customerRepo;
  private final ThirdPartyPricesApi thirdPartyPricesApi;
  private final CouponRepo couponRepo;
  private final ProductRepo productRepo;

  public PureFunction(CustomerRepo customerRepo, ThirdPartyPricesApi thirdPartyPricesApi, CouponRepo couponRepo, ProductRepo productRepo) {
    this.customerRepo = customerRepo;
    this.thirdPartyPricesApi = thirdPartyPricesApi;
    this.couponRepo = couponRepo;
    this.productRepo = productRepo;
  }

  // TODO extract complexity into a pure function
  public Map<Long, Double> computePrices(long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
    Customer customer = customerRepo.findById(customerId);
    List<Product> products = productRepo.findAllById(productIds); // SELECT * WHERE id IN (1,2,3,...)
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.id());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.id());
      }
      initialPrices.put(product.id(), price);
    }
    ApplyCouponsResults result = applyCoupons(products, initialPrices, customer.coupons());
    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }
//   TS const {usedCoupons, finalPrices}  = applyCoupons(products, initialPrices, customer);
//   C# var (usedCoupons, finalPrices) = applyCoupons(products, initialPrices, customer);
//   PY usedCoupons, finalPrices  = applyCoupons(products, initialPrices, customer);

  record ApplyCouponsResults(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {  }
  // PURE function: da la fel si nu schimba stare in exterior
  @VisibleForTesting // crapa sonaru daca e chemata din alte clase din src/main; doar @Teste au voie la ea
  static ApplyCouponsResults applyCoupons(List<Product> products, Map<Long, Double> initialPrices, List<Coupon> coupons) {
    List<Coupon> usedCoupons = new ArrayList<>();
    Map<Long, Double> finalPrices = new HashMap<>();
    for (Product product : products) {
      double price = initialPrices.get(product.id());
      for (Coupon coupon : coupons) {
        if (canApplyCoupon(product, coupon, usedCoupons)) {
          price = coupon.apply(product, price);
          usedCoupons.add(coupon);
        }
      }
      finalPrices.put(product.id(), price);
    }
    return new ApplyCouponsResults(usedCoupons, finalPrices);
  }

  private static boolean canApplyCoupon(Product product, Coupon coupon, List<Coupon> usedCoupons) {
    return coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon);
  }

}

