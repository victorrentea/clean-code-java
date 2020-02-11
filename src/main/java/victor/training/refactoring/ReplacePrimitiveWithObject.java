package victor.training.refactoring;

import lombok.Data;

import java.util.List;

public class ReplacePrimitiveWithObject {
    public void displayUrgent(List<Incident> incidents) {
        incidents.stream()
                .filter(Incident::aboveNormal)
                .forEach(System.out::println);
    }
}

class Priority {
    private final String value;
    Priority(String value) {
        this.value = value;
    }

    public boolean aboveNormal() {
        return value.equals("high") || value.equals("rush");
    }
}

@Data
class Incident {
    private final Long id;
    private final Priority priority;

    public boolean aboveNormal() {
        return priority.aboveNormal();
    }
}