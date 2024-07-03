package victor.training.cleancode.exception.model;

import java.util.Optional;

public class Customer {
   private String name;
   private MemberCard memberCard;

   public Customer() {
   }

   public Customer(MemberCard memberCard) {
      this.memberCard = memberCard;
   }

   // am mers pe sfantu meu Model
   public Optional<MemberCard> getMemberCard() {
      return Optional.ofNullable(memberCard);
   }

   public Customer setMemberCard(MemberCard memberCard) {
      this.memberCard = memberCard;
      return this;
   }
}
