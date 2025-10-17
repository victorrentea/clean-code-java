package victor.training.cleancode.exception.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Customer {
   private String name;
   private MemberCard memberCard;
  private List<String> tags = new ArrayList<>();

   public Customer() {
   }

   public Customer(MemberCard memberCard) {
      this.memberCard = memberCard;
   }

   public Optional<MemberCard> getMemberCard() {
      return Optional.ofNullable(memberCard);
   }

   public Customer setMemberCard(MemberCard memberCard) {
      this.memberCard = memberCard;
      return this;
   }
}
