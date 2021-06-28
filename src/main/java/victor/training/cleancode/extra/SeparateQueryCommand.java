package victor.training.cleancode.extra;

import java.util.List;

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

    private void setOffAlarms() {
        System.out.println("Side effects\n");
    }
}
