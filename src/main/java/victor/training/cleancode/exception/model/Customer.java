package victor.training.cleancode.exception.model;

import java.util.Optional;

import static java.util.Optional.ofNullable;

// Domain Model, principala sursa de nulluri
public class Customer {
   private String name;
   private MemberCard memberCard;

   public Customer() {
   }

   public Customer(MemberCard memberCard) {
      this.memberCard = memberCard;
   }

   public Optional<MemberCard> getMemberCard() { // 10 calleri in code
      return Optional.ofNullable(memberCard);
   }

   public Customer setMemberCard(MemberCard memberCard) {
      this.memberCard = memberCard;
      return this;
   }
}
