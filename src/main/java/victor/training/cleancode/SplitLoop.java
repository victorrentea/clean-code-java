package victor.training.cleancode;

import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

/** Break the loops and refactor to use .stream to compute stuff. */
public class SplitLoop {
  // run tests
  public String computeStats(List<Employee> employees) {
    double totalConsultantSalary = employees.stream()
        .filter(Employee::consultant)
        .mapToDouble(Employee::salary)
        .sum();

    List<Integer> employeeIds = employees.stream()
        .map(Employee::id)
        .collect(Collectors.toList());

    System.out.println("Employee IDs: " + employeeIds);
    return "Total consultant salary: " + totalConsultantSalary + "; ids: " + employeeIds;
  }

  @Builder(toBuilder = true)
  public record Employee(
      Integer id,
      int age,
      Integer salary,
      boolean consultant) {
  }
}

