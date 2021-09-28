package victor.training.exception.model;

import java.util.Date;

import static java.util.Objects.requireNonNull;

public class Order {
   private long id;
   private int price;
   private Date offerDate;

   public Order(Date offerDate) {
      setOfferDate(offerDate);
   }

   public long getId() {
      return id;
   }

   public int getPrice() {
      return price;
   }

   public Order setPrice(int price) {
      this.price = price;
      return this;
   }

   public Date getOfferDate() {
      return offerDate;
   }

   public Order setOfferDate(Date offerDate) {
      this.offerDate = requireNonNull(offerDate);
      return this;
   }
}
