package victor.training.cleancode.fp;

import lombok.Data;

import java.util.Optional;

public class OptionalAbuse {
  public Entity trappedOptional(Dto dto) {
    Entity entity = new Entity();
    // paranoid development?// NULL is even possible in the broader scope? NO
    var email = dto.recipientPerson
                    .map(s -> s.trim().toLowerCase())
        .filter(s -> !s.isBlank())
                    .orElse("anonymous") + "@example.com";
    entity.setRecipient(email);
    entity.setAge(dto.age());
    return entity;
  }

  public record Dto(Optional<String> recipientPerson, int age) {
  }

  @Data
  public static class Entity {
    private String recipient;
    private int age;
  }
}
