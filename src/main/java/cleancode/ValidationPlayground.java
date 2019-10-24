package cleancode;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

public class ValidationPlayground {
    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        DataClass dataClass = new DataClass();
        System.out.println(validator.validate(dataClass, DataClass.ActiveStatus.class)); //fails
        System.out.println(validator.validate(dataClass)); // passes;
    }
}

class DataClass {
    interface ActiveStatus {}
    interface DraftStatus {}
    @NotNull(groups = {ActiveStatus.class, DraftStatus.class})
    String name;
}
