package victor.training.refactoring;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SplitLoop {

    private String computeStats(List<Employee> employees) {
        long averageAge = 0;
        double averageSalary = 0;
        for (Employee employee : employees) {
            if (!employee.isConsultant())
            averageAge += employee.getAge();
            averageSalary += employee.getSalary();
        }
        averageAge = averageAge / employees.stream().filter(e -> !e.isConsultant()).count();
        averageSalary = averageSalary / employees.size();
        return "avg age = " + averageAge + "; avg sal = " + averageSalary;
    }

    @Test
    public void test() {
        String actual = computeStats(asList(
                new Employee(24, 2000, false),
                new Employee(28, 1500, true),
                new Employee(30, 2500, true)));
        assertEquals("avg age = 24; avg sal = 2000.0", actual);
    }
}


@Data
class Employee {
    private final int age;
    private final double salary;
    private final boolean consultant;
}