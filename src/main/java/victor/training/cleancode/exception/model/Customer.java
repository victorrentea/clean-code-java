package victor.training.cleancode.exception.model;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Customer {
   private String name;
   private MemberCard memberCard; // nu Optional

   public Customer() {
   }

   public Customer(MemberCard memberCard) {
      this.memberCard = memberCard;
   }

   //faceti si voi asa ? din getter entitat sa intori optional
   public Optional<MemberCard> getMemberCard() {
      return Optional.ofNullable(memberCard);
   }

   public Customer setMemberCard(MemberCard memberCard) { // nu Optional
      this.memberCard = memberCard;
      return this;
   }
}
