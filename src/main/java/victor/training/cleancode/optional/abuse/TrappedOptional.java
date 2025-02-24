package victor.training.cleancode.optional.abuse;

import java.util.Optional;

public class TrappedOptional {
  public void kafkaSend(String personName) {
  }
  static class MyDto {
    public String recipientPerson;
  }
  static class MyEntity {
    private String recipient;

    public String getRecipient() {
      return recipient;
    }

    public void setRecipient(String recipient) {
      this.recipient = recipient;
    }
  }

  public void trappedOptional(MyDto dto) {
    MyEntity entity = new MyEntity();
//    Optional.ofNullable(dto.recipientPerson)
//            .map(String::toUpperCase)
//            .ifPresent(name -> entity.setRecipient(name));

    if (dto.recipientPerson != null) {
      entity.setRecipient(dto.recipientPerson.toUpperCase());
    }

  }

  public void trappedOptionalWithExternalSideEffect(MyDto dto) {
    Optional.ofNullable(dto.recipientPerson)
            .map(String::toUpperCase)
            .ifPresent(name -> kafkaSend(name));
  }
}
