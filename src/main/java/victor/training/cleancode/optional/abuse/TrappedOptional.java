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
    // you start a chain of calls with Optional.ofNullable but you never return /pass out htat Optional
    //    Optional.ofNullable(dto.recipientPerson)
    //            .map(String::toUpperCase)
    //            .ifPresent(name -> {
    //              entity.setRecipient(name);
    //            });

    // better:
    if (dto.recipientPerson != null) {
      entity.setRecipient(dto.recipientPerson.toUpperCase());
    }
    // also: if the dto.recipientPerson would be Optional, back on first form

    //    entity.setRecipient(Optional.ofNullable(dto.recipientPerson)
    //            .map(String::toUpperCase)
    //            .orElse(null));
  }


  public void trappedOptionalWithExternalSideEffect(MyDto dto) {
    Optional.ofNullable(dto.recipientPerson)
            .map(String::toUpperCase)
            .ifPresent(this::kafkaSend);

    if (dto.recipientPerson != null) {
      kafkaSend(dto.recipientPerson.toUpperCase());
    }
  }
}
