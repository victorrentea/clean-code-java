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

   //
   //   public MemberCard getMemberCard() {
   //      return getMemberCardOpt().orElse(new MemberCard(0));
   //   }
   public Optional<MemberCard> getMemberCard() {
      //      return memberCard != null ? memberCard : new MemberCard(0);
      return Optional.ofNullable(memberCard);
      // PANICA
   }

   public Customer setMemberCard(MemberCard memberCard) {
      this.memberCard = memberCard;
      return this;
   }
}
