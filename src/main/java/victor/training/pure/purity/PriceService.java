package victor.training.pure.purity;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PriceService {
   private final CustomerRepo customerRepo;
   private final ThirdPartyPrices thirdPartyPrices;
   private final CouponRepo couponRepo;
   private final ProductRepo productRepo;

   public Map<Long, Double> computePrices(long customerId, List<Long> productIds, Map<Long, Double> internalPrices) {
      Customer customer = customerRepo.findById(customerId);
      List<Product> products = productRepo.findAllById(productIds);

      Map<Long, Double> resolvedPrices = resolvePrices(internalPrices, products);

      PriceComputationResult result = computePrice(customer, products, resolvedPrices);

      couponRepo.markUsedCoupons(customerId, result.getUsedCoupons());
      return result.getFinalPrices();
   }

   // PURE FUNCTION: best part: testable wihtout MOCKS
   private PriceComputationResult computePrice(Customer customer, List<Product> products, Map<Long, Double> resolvedPrices) {
      List<Coupon> usedCoupons = new ArrayList<>();
      Map<Long, Double> finalPrices = new HashMap<>();
      for (Product product : products) {
         Double price = resolvedPrices.get(product.getId());
         for (Coupon coupon : customer.getCoupons()) {
            if (coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
               price = coupon.apply(product, price);
               usedCoupons.add(coupon);
            }
         }
         finalPrices.put(product.getId(), price);
      }
      return new PriceComputationResult(finalPrices, usedCoupons);
   }

   @Value
   static class PriceComputationResult {
      Map<Long, Double> finalPrices;
      List<Coupon> usedCoupons;
   }

   private Map<Long, Double> resolvePrices(Map<Long, Double> internalPrices, List<Product> products) {
      Map<Long, Double> resolvedPrices = new HashMap<>();
      for (Product product : products) {
         Double price = internalPrices.get(product.getId());
         if (price == null) {
            price = thirdPartyPrices.retrievePrice(product.getId());
         }
         resolvedPrices.put(product.getId(), price);
      }
      return resolvedPrices;
   }

}

