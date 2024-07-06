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
    List<Integer> employeeIds = new ArrayList<>();
    double totalConsultantSalary = 0;
    double averageSalary = 0;
    for (Employee employee : employees) {
      averageSalary += employee.salary();
      if (employee.consultant()) {
        totalConsultantSalary += employee.salary();
      }
      employeeIds.add(employee.id());
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