package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {
  // run tests
  public String computeStats(List<Employee> employees) {
    double totalConsultantSalary = 0;
    double averageSalary;


    List<Integer> employeeIds = new ArrayList<>();
    for (Employee employee : employees) {
      employeeIds.add(employee.id());
    }


    averageSalary = employees.stream().mapToDouble(Employee::salary).sum();


    for (Employee employee : employees) {
      if (employee.consultant()) {
        totalConsultantSalary += employee.salary();
      }
    }
    f(averageSalary);
    System.out.println("Employee IDs: " + employeeIds);
    averageSalary /= employees.size();
    return "Total consultant salary: " + totalConsultantSalary + "; Average salary = " + averageSalary + " of " + employeeIds;
  }

  private void f(double averageSalary) {
    System.out.println("Average salary: " + averageSalary);
  }
}

@Builder(toBuilder = true)
record Employee(
    Integer id,
    int age,
    Integer salary,
    boolean consultant) {
}