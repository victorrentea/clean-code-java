package victor.training.cleancode;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.ArrayList;
import java.util.List;

public class ManyParamsOOP {
    @Autowired
    private Validator validator;

    public void bizLogic() {
        List<String> errors = new ArrayList<>();
        validator.m1(errors, "a",1);
        validator.m2(errors, "b",1);
        validator.m3(errors, "file.txt", 1L,"ref");
        validator.m4("a", 1L,5L, "g", errors);
        validator.m5(1, errors);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
@Service
class Validator {
    @Autowired
	private OtherDependency dep;

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
    public void m4(String a, long listId, long recordId, String g, List<String> errors) {
        // stuff
    }
    public void m5(int b, List<String> errors) {
        // stuff
    }
}

@Service
class OtherDependency {

}
