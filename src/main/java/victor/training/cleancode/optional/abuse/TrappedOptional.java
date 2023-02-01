package victor.training.cleancode.optional.abuse;

import lombok.Data;

import java.util.Optional;

public class TrappedOptional {
  public void kafkaSend(String personName) {
  }
  static class MyDto {
    public String recipientPerson;
  }
  @Data
  static class MyEntity {
    private String recipient;
  }

  public void trappedOptional(MyDto dto) {
    MyEntity entity = new MyEntity();
    Optional.ofNullable(dto.recipientPerson)
            .map(String::toUpperCase)
            .ifPresent(name -> {
              entity.setRecipient(name);
            });
  }

  public void trappedOptionalWithExternalSideEffect(MyDto dto) {
    Optional.ofNullable(dto.recipientPerson)
            .map(String::toUpperCase)
            .ifPresent(name -> {
              kafkaSend(name);
            });
  }
}
