package victor.training.cleancode.optional.abuse;

import lombok.Data;

import java.util.Optional;

public class TrappedOptional {
  static class MyDto {
    public String recipientPerson;
  }
  @Data
  static class MyEntity {
    private String recipient;
  }

  public void trappedOptional(MyDto dto) {
    MyEntity entity = new MyEntity();
//    Optional.ofNullable(dto.recipientPerson)
//            .map(String::toUpperCase)
//            .ifPresent(name -> {
//              entity.setRecipient(name);
//            });
    if (dto.recipientPerson != null) {
      entity.setRecipient(dto.recipientPerson.toUpperCase());
    }
  }
}
