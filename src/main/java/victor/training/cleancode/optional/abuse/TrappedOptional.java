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
//    sendKafka();
//            });
    String name;
    if (dto.recipientPerson != null) {
      name = dto.recipientPerson.toUpperCase();
    } else {
      name = fallback();
    }
    entity.setRecipient(name);
  }

  private String fallback() {
    throw new RuntimeException("Method not implemented");
  }

  public void trappedOptionalWithoutSIdeEffects(MyDto dto) {
    MyEntity entity = new MyEntity();
    String name = Optional.ofNullable(dto.recipientPerson)
            .map(String::toUpperCase)
            .orElse(fallback());
    entity.setRecipient(name);
  }
}
