package victor.training.cleancode.refactoring;

import java.util.List;

public class SeparateQueryCommand {

    public String alertForMiscreant(List<String> people) {
        for (String person : people) {
          if ("Don".equals(person)) {
                setOffAlarms();
                return "Don";
            }
          if ("John".equals(person)) {
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
