package victor.training.cleancode.support;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private final Integer id;
    private final int age;
    private Integer salary;
    private final boolean consultant;
}