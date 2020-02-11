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
        int averageAge = calculateTotalAge(employees) / employees.size();
        double averageSalary = calculateTotalSalary(employees) / employees.size();
        System.out.println("avg age = " + averageAge + "\navg sal = " + averageSalary);
        // TODO pipeline
    }

    private static int calculateTotalAge(List<Employee> employees) {
        return employees.stream().mapToInt(Employee::getAge).sum();
    }

    private static double calculateTotalSalary(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }
}


@Data
class Employee {
    private final int age;
    private final double salary;
}