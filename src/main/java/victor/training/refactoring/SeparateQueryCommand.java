package victor.training.refactoring;

import java.util.List;

import static java.util.Arrays.asList;

public class SeparateQueryCommand {

    public static final List<String> SPECIAL_PEOPLE = asList("Don", "John");

    // imperative shell
    public String alertForMiscreant(List<String> people) {
        String miscreant = getMiscreant(people);
        if (!miscreant.isEmpty()) {
            setOffAlarms();
        }
        return miscreant;
    }
    // pure function!
    public String getMiscreant(List<String> people) {
        return people.stream().filter(SPECIAL_PEOPLE::contains).findFirst().orElse("");
    }

    private void setOffAlarms() {
        System.out.println("Side effects\n");
    }

    public static void main(String[] args) {
        String use = new SeparateQueryCommand().alertForMiscreant(asList("asdsa", "aasda"));
        System.out.println(use);
    }
}
