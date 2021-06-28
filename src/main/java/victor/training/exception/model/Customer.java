package victor.training.exception.model;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Customer {
   private String name;
   private MemberCard memberCard;

   public Optional<MemberCard> getMemberCard() {
      return ofNullable(memberCard);
   }

   public Customer setMemberCard(MemberCard memberCard) {
      this.memberCard = memberCard;
      return this;
   }
}
