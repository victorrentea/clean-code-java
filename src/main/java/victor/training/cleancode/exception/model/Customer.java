package victor.training.cleancode.exception.model;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Customer {
   private String name;
   private MemberCard memberCard;

   public Customer() {
   }

   public Customer(MemberCard memberCard) {
      this.memberCard = memberCard;
   }

   public MemberCard getMemberCard() {
      return memberCard;
   }

   public Customer setMemberCard(MemberCard memberCard) {
      this.memberCard = memberCard;
      return this;
   }
}
