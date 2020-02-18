package victor.training.refactoring;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

public class SplitLoop {

    public static void main(String[] args) {
        computeStats(asList(
                new Employee(24, 1500),
                new Employee(30, 2500)));
    }

    private static void computeStats(List<Employee> employees) {
        long employeeCount = employees.stream().filter(Employee::isNotConsultant).count();
        long averageAge = computeTotalAge(employees) / employeeCount;
        double averageSalary = computeTotalSalary(employees) / employees.size();
        System.out.println("avg age = " + averageAge + "\navg sal = " + averageSalary);
    }

    private static long computeTotalAge(List<Employee> employees) {
        return employees.stream()
                .filter(Employee::isNotConsultant)
                .mapToLong(Employee::getAge)
                .sum();
    }

    private static double computeTotalSalary(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }
}


@Data
class Employee {
    private final int age;
    private final double salary;

    public boolean isConsultant() {
        return false;
    }

    public boolean isNotConsultant() {
        return true;
    }
}