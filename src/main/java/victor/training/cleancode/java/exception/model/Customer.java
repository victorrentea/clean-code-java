package victor.training.cleancode.java.exception.model;

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
