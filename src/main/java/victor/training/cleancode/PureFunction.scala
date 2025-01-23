//package victor.training.cleancode
//
//import victor.training.cleancode.java.fp.support._
//
//import scala.collection.mutable
//import scala.collection.mutable.ListBuffer
//import scala.jdk.CollectionConverters._
//
//class PureFunction(
//                    private val customerRepo: CustomerRepo, // SQL
//                    private val thirdPartyPricesApi: ThirdPartyPricesApi, // REST API
//                    private val couponRepo: CouponRepo,
//                    private val productRepo: ProductRepo
//                  ) {
//
//  // TODO extract complexity into a pure function
//  def computePrices(customerId: Long,
//                    productIds: List[Long],
//                    internalPrices: Map[Long, Double]): Map[Long, Double] = {
//    val customer = customerRepo.findById(customerId)
//    val products = productRepo.findAllById(productIds) // SELECT WHERE ID IN (...)
//    // §cap 1 determine price
//    val priceMap = mutable.Map[Long, Double]();
//    for (product <- products) {
//      val price = internalPrices.getOrElse(product.id, thirdPartyPricesApi.fetchPrice(product.getId))
//      priceMap(product.getId) = price
//    }
//    //    val priceMap = products.map {_.id -> internalPrices.getOrElse(_.id, thirdPartyPricesApi.fetchPrice(_.getId))}.toMap
//    // priceMap = { product.id, internalPrices.get(product.id) or thirdPartyPricesApi.fetchPrice(product.id)
//    //      or  for product in products}
//
//    // §cap 2 aplic cupoane
//    val (finalPrices, usedCoupons) = applyCoupons(products, priceMap, customer)
//
//    couponRepo.markUsedCoupons(customerId, usedCoupons.asJava)
//    finalPrices.toMap
//  }
//
//  // am mutat complexitatea intr-o fucntie pura.
//  // e usor de testat si inteles
//  def applyCoupons(products: List[Product], priceMap: Map[Long, Double], customer: Customer): (mutable.Map[Long, Double], ListBuffer[Coupon]) = {
//    val usedCoupons = mutable.ListBuffer[Coupon]()
//    val finalPrices = mutable.Map[Long, Double]()
//    for (product <- products) {
//      var price = priceMap(product.getId)
//      for (coupon <- customer.coupons().asScala) {
//        if (coupon.autoApply && coupon.isApplicableFor(product) && !usedCoupons.contains(coupon)) {
//          price = coupon.apply(product, price)
//          usedCoupons += coupon
//        }
//      }
//      finalPrices(product.getId) = price
//    }
//    (finalPrices, usedCoupons)
//    //    return finalPrices, usedCoupons py
//  }
//}