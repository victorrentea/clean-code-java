package victor.training.exception.model;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Customer {
   private String name;
   private MemberCard memberCard;// = MemberCard.NO_CARD; // NULL OBJECT DESIGN PATTERN e o alternativa la Optional
   // diferenta este ca iti asumi tu in model sa creezi o valoare cu date "ca si cum ar lipsi"

   public Customer() {
   }

   public Customer(MemberCard memberCard) {
      this.memberCard = memberCard;
   }

   public Optional<MemberCard> getMemberCard() {
      return Optional.ofNullable(memberCard);
   }

   public Customer setMemberCard(MemberCard memberCard) {
//      if (memberCard == null) {
//         memberCard = MemberCard.NO_CARD;
//      }
      this.memberCard = memberCard;
      return this;
   }
}
