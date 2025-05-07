package victor.training.cleancode.fp;

import lombok.Data;

import java.util.Optional;

public class E4_Optionality {
  public Entity trappedOptional(Dto dto) {
    Entity entity = new Entity();
    Optional.ofNullable(dto)
        .map(Dto::recipientPerson)
        .map(String::trim)
        .map(String::toLowerCase)
        .filter(s -> !s.isBlank())
        .map(s -> s + "@example.com")
        .ifPresentOrElse(
            email -> entity.setRecipient(email),
            () -> entity.setRecipient("anonymous@example.com")
        );
    entity.setAge(dto.age());
    return entity;
  }

  public record Dto(String recipientPerson, int age) {
  }

  @Data
  public static class Entity {
    private String recipient;
    private int age;
  }
}
