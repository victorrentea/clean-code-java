package victor.training.cleancode.extra;

import lombok.Data;

import java.util.List;

public class SplitLoop {

    // see tests
    public static String computeStats(List<Employee> employees) {
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


}


@Data
class Employee {
    private final int age;
    private final double salary;
    private final boolean consultant;
}