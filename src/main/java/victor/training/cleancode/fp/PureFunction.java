package victor.training.cleancode.fp;

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

    Map<Long, Double> initialPrices = resolveInitialPrices(internalPrices, products);

    ApplyCouponsResults result = applyCoupons(products, initialPrices, customer.coupons());
//   PY usedCoupons, finalPrices  = applyCoupons(products, initialPrices, customer);
//   TS const {usedCoupons, finalPrices}  = applyCoupons(products, initialPrices, customer);
//   C# var (usedCoupons, finalPrices) = applyCoupons(products, initialPrices, customer);

    couponRepo.markUsedCoupons(customerId, result.usedCoupons());
    return result.finalPrices();
  }
  private record ApplyCouponsResults(List<Coupon> usedCoupons, Map<Long, Double> finalPrices) {
  }
  // PURE function: da la fel si nu schimba stare in exterior
  private ApplyCouponsResults applyCoupons(List<Product> products, Map<Long, Double> initialPrices, List<Coupon> coupons) {
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

  private Map<Long, Double> resolveInitialPrices(Map<Long, Double> internalPrices, List<Product> products) {
    Map<Long, Double> initialPrices = new HashMap<>();
    for (Product product : products) {
      Double price = internalPrices.get(product.id());
      if (price == null) {
        price = thirdPartyPricesApi.fetchPrice(product.id());
      }
      // #1 product.setPrice(price); // price va fi un atribut transient/volatil pus pe Product = "Temporary Field code smell"
      // Insa daca Product este folosita in mii de linii, asta nu e o idee buna dpdv clean code.
      // Mii de linii se vor intreba ce e cu acel atribut

      // #2
      // internalPrices.put(product.id(), price); // side effect pe starea din caller; ii schimb mapa callerului

      // #3 intr-o lista accesata ulterior dupa index (anii 90')
      //  prices.add(price);

      // #4 produci o colectie noua.
      initialPrices.put(product.id(), price);
    }
    return initialPrices;
  }

  private boolean canApplyCoupon(Product product, Coupon coupon, List<Coupon> usedCoupons) {
    return coupon.autoApply() && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon);
  }
}

