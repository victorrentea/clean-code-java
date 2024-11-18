package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {
  // run tests
  public String computeStats(List<Employee> list) {
    double totalConsultantSalary = 0;
    double averageSalary;


    List<Integer> employeeIds = new ArrayList<>();
    for (Employee employee : list) {
      employeeIds.add(employee.id());
    }


    averageSalary = list.stream().mapToDouble(Employee::salary).sum();


    for (Employee employee : list) {
      if (employee.consultant()) {
        totalConsultantSalary += employee.salary();
      }
    }
    f(averageSalary);
    System.out.println("Employee IDs: " + employeeIds);
    averageSalary /= list.size();
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