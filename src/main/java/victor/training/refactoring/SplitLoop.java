package victor.training.refactoring;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SplitLoop {

    private String computeStats(List<Employee> employees) {
        return "avg age = " + averageAge(employees) + "; avg sal = " + averageSalary(employees);
    }

    private double averageSalary(List<Employee> employees) {
        return employees.stream()
            .mapToDouble(Employee::getSalary)
            .average().orElse(0);
    }

    private long averageAge(List<Employee> employees) {
        return (long) employees.stream()
            .filter(employee1 -> !employee1.isConsultant())
            .mapToLong(Employee::getAge)
            .average()
            .orElse(0d);
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


class Employee {
    private final int age;
    private final double salary;
    private final boolean consultant;

    public Employee(int age, double salary, boolean consultant) {
        this.age = age;
        this.salary = salary;
        this.consultant = consultant;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isConsultant() {
        return consultant;
    }
}