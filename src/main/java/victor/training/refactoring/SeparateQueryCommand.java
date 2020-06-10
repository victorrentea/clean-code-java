package victor.training.refactoring;

import java.util.List;

import static java.util.Arrays.asList;

public class SeparateQueryCommand {

    public String alertForMiscreant(List<String> people) {
        for (String person : people) {
            if (person.equals("Don")) {
                setOffAlarms();
                return "Don";
            }
            if (person.equals("John")) {
                setOffAlarms();
                return "John";
            }
        }
        return "";
    }


    // pure query function
    public String getMiscreant(List<String> people) {
        for (String person : people) {
            if (asList("Don", "John").contains(person)) {
                return person;
            }
        }
        return "";
    }

    private void setOffAlarms() {
        System.out.println("Side effects\n");
    }
}
