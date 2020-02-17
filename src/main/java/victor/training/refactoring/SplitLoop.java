package victor.training.refactoring;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class SplitLoop {

    public static void main(String[] args) {
        computeStats(Arrays.asList(
                new Employee(24, 1500),
                new Employee(30, 2500)));
    }

    private static void computeStats(List<Employee> employees) {
        long averageAge = 0;
        double averageSalary = 0;
        for (Employee employee : employees) {
            if (!employee.isConsultant())
            averageAge += employee.getAge();
            averageSalary += employee.getSalary();
        }
        averageAge = averageAge / employees.stream().filter(e -> !e.isConsultant()).count();
        averageSalary = averageSalary / employees.size();
        System.out.println("avg age = " + averageAge + "\navg sal = " + averageSalary);
    }
}


@Data
class Employee {
    private final int age;
    private final double salary;

    public boolean isConsultant() {
        return false;
    }
}