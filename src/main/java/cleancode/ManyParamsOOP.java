package cleancode;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ManyParamsOOP {

    public void bizLogic() {
        Validator validator = new Validator();
        List<Supplier<List<String>>> errorSupplier = Arrays.asList(
                () -> validator.m1("a",1),
                () -> validator.m2("b",1)
        );

        errorSupplier.stream().map(Supplier::get).flatMap(List::stream).collect(Collectors.toList());

        List<String> errors = new ArrayList<>();
        errors.addAll(validator.m1("a",1));
        errors.addAll( validator.m2("b",1));


//        validator.m3("file.txt", 1L,"ref");
//        validator.m4("a", 1L,5L, "g");
//        validator.m5(1);
//        if (!validator.getErrors().isEmpty()) {
//            throw new IllegalArgumentException(validator.getErrors().toString());
//        }
    }
}
class Validator {

//    private List<String> errors = new ArrayList<>();

//    public List<String> getErrors() {
//        return errors;
//    }

    public List<String> m1(String a, int b) {
        List<String> errors = new ArrayList<>();
        if (a == null) {
            errors.add("a must not be null");
        }
        return errors
                ;
        // stuff
    }
    public  List<String> m2(String s, int c) {
        List<String> errors = new ArrayList<>();
        if (c < 0) {
            errors.add("negative c");
        }
        return errors;
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
