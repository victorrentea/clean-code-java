package cleancode;

import java.util.ArrayList;
import java.util.List;

public class ManyParamsOOP {
    // @Autowired/@Inject // pretend
    private Validator validator = new Validator();

    public void bizLogic() {
        List<String> errors = new ArrayList<>();
        validator.m1(errors, "a",1);
        validator.m2(errors, "b",1);
        validator.m3(errors, "file.txt", 1L,"ref");
        validator.m4(errors, "a", 1L,5L, "g");
        validator.m5(errors, 1);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
// @Service ~pretend
class Validator {
	// @Autowired/@Inject // pretend
	//private OtherDependency dep;

    public void m1(List<String> errors, String a, int b) {
        if (a == null) {
            errors.add("a must not be null");
        }
        // stuff
    }
    public void m2(List<String> errors, String s, int c) {
        if (c < 0) {
            errors.add("negative c");
        }
        // stuff
    }
    public void m3(List<String> errors, String fileName, long versionId, String reference) {
        // stuff
    }
    public void m4(List<String> errors, String a, long listId, long recordId, String g) {
        // stuff
    }
    public void m5(List<String> errors, int b) {
        // stuff
    }
}
