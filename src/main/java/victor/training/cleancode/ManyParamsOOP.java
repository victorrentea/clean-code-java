package victor.training.cleancode;

import victor.training.cleancode.pretend.Autowired;
import victor.training.cleancode.pretend.Service;

import java.util.ArrayList;
import java.util.List;

public class ManyParamsOOP {
    @Autowired
    private OtherDependency dep;

    public void bizLogic() {
        Validator validator = new Validator(dep, 1);
        validator.getErrors().clear();
        validator.m1("a");
        validator.m2("b");
        validator.m3("file.txt", "ref");
        validator.m4("a", "g");
        validator.m5();
        List<String> errors = validator.getErrors();
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
class Validator {
	private final OtherDependency dep;
    private final int storeId;
    private final List<String> errors = new ArrayList<>();

    Validator(OtherDependency dep, int storeId) {
        this.dep = dep;
        this.storeId = storeId;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void m1(String a) {
        if (a == null) {
            errors.add("a must not be null");
        }
        // stuff
    }
    public void m2(String s) {
        if (storeId < 0) {
            errors.add("negative c");
        }
        // stuff
    }
    public void m3(String fileName, String reference) {
        // stuff
    }
    public void m4(String a, String g) {
        // stuff
    }
    public void m5() {
        // stuff
    }
}

@Service
class OtherDependency {

}
