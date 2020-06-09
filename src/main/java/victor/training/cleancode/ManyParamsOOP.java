package victor.training.cleancode;

import victor.training.cleancode.pretend.Service;

import java.util.ArrayList;
import java.util.List;

public class ManyParamsOOP {
//    @Autowired -- NU
//    private Validator validator;

//    @Autowired
//    @Inject
//    @EJB
    private OtherDependency dep;

    public void bizLogic() {
        Validator validator = new Validator(dep); // NEW doare pt ca tre sa ii oferi dependentele manual
        validator.m1("a",1);
        validator.m2("b",1);
        validator.m3("file.txt", 1L,"ref");
        validator.m4("a", 1L,5L, "g");
        validator.m5(1);
        List<String> errors = validator.getErrors();
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
//@Service @Stateless @Named -NU POATE FI
class Validator { // stateful pentru ca tine minte date specifice procesarii in curs
    // ==> nu mai poate fi singletoana (adica nu mai poti sa-ti injectezi un validator cand iti tuna)
//    @Autowired
//    @Inject
//    @EJB
	private final OtherDependency dep;

    private List<String> errors = new ArrayList<>();

    Validator(OtherDependency dep) {
        this.dep = dep;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void m1(String a, int b) {
        if (a == null) {
            errors.add("a must not be null");
        }
        // stuff
    }
    public void m2(String s, int c) {
        if (c < 0) {
            errors.add("negative c");
        }
        // stuff
    }
    public void m3(String fileName, long versionId, String reference) {
        // stuff
    }
    public void m4(String a, long listId, long recordId, String g) {
        // stuff
    }
    public void m5(int b) {
        // stuff
    }
}

@Service
class OtherDependency {

}
