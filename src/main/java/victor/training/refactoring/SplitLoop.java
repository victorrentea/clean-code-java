package victor.training.refactoring;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SplitLoop {

   private String computeStats(List<Employee> employees) {
      return "avg age = " + computeAverageAge(employees) +
             "; avg sal = " + computeAverageSalary(employees);
   }

   private double computeAverageSalary(List<Employee> employees) {
      double totalSallary = 0;
      for (Employee employee : employees) {
         totalSallary += employee.getSalary();
      }
      return totalSallary / employees.size();
   }

   private long computeAverageAge(List<Employee> employees) {
      long totalAge = employees.stream()
          .filter(Employee::isNotConsultant)
          .mapToLong(Employee::getAge)
          .sum();
      return totalAge / employees.stream().filter(Employee::isNotConsultant).count();
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

   public boolean isNotConsultant() {
      return !isConsultant();
   }
}