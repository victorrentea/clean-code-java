package cleancode;

import java.util.ArrayList;
import java.util.List;

public class ManyParamsOOP {
    public static void main(String[] args) {

    }

    //@Inject // pretend
//    private Validator validator = new Validator();

    public void bizLogic() {
		Validator validator = new Validator(/* dependente */);
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
//@Service @Statless
class Validator {
//	@Autowired/@Inmject ceva 
	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}
	
    public void m1(String a, int b) {
        // stuff
    }
    public void m2(String s, int c) {
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
