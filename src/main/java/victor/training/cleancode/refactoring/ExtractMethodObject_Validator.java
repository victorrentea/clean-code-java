package victor.training.cleancode.refactoring;


import java.util.ArrayList;
import java.util.List;

public class ExtractMethodObject_Validator {
    private final Validator validator;

  public ExtractMethodObject_Validator(Validator validator) {
    this.validator = validator;
  }

  public void bizLogic() {
        List<String> errors = new ArrayList<>();
        validator.m1("a",1, errors);
        validator.m2("b",1, errors);
        validator.m3("file.txt", 1L,"ref", errors);
    validator.m4("a", 1L, 5L, "gg", errors);
        validator.m5(1, errors);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
//@Service
class Validator {
//    @Autowired
	private OtherDependency dep;

    public void m1(String a, int b, List<String> errors) {
        if (a == null) {
            errors.add("a must not be null");
        }
        // stuff
    }
    public void m2(String s, int c, List<String> errors) {
        if (c < 0) {
            errors.add("negative c");
        }
        // stuff
    }
    public void m3(String fileName, long versionId, String reference, List<String> errors) {
        // stuff
    }
    public void m4(String a, long listId, long recordId, String g, List<String> errors) {
        // stuff
    }
    public void m5(int b, List<String> errors) {
        // stuff
    }
}

//@Service
class OtherDependency {

}
