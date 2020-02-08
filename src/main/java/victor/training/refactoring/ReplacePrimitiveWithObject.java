package victor.training.refactoring;

import lombok.Data;

import java.util.List;

public class ReplacePrimitiveWithObject {
    public void displayUrgent(List<Incident> incidents) {
        incidents.stream()
                // over 'normal'
                .filter(i-> i.getPriority().equals("high") || i.getPriority().equals("rush"))
                .forEach(System.out::println);
    }
}

@Data
class Incident {
    private final Long id;
    private final String priority;
}