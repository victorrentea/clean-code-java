package victor.training.cleancode;

import lombok.Data;

import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class Task {
  @Min(0)
  private final int id;
  private boolean started;
  @NotBlank
  String name;
  @Email
  String email;

  public static void main(String[] args) {
    ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
    factory.getValidator().validate(new Task(-1));
  }
}



