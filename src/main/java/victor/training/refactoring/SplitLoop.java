package victor.training.refactoring;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SplitLoop {

    private String computeStats(List<Employee> employees) {
       double averageSalary = computeAverageSalary(employees);
       long averageAge = computeAverageAge(employees);
       return "avg age = " + averageAge + "; avg sal = " + averageSalary;
    }

   private long computeAverageAge(List<Employee> employees) {
//      long totalAge = employees.stream().filter(employee -> !employee.isConsultant()).mapToLong(Employee::getAge).sum();
//      return totalAge / employees.stream().filter(e -> !e.isConsultant()).count();

      return (long) employees.stream()
          .filter(Employee::isNotConsultant)
          .mapToLong(Employee::getAge)
          .average()
          .orElse(0);
   }

   private double computeAverageSalary(List<Employee> employees) {
      return employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
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

    Employee(int age, double salary, boolean consultant) {
        this.age = age;
        this.salary = salary;
        this.consultant = consultant;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isConsultant() {
        return consultant;
    }

    public int getAge() {
        return age;
    }

   public boolean isNotConsultant() {
      return !consultant;
   }
}