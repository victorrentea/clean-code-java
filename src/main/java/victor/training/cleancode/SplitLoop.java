package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SplitLoop {
  // TODO Split loops and refactor to .stream. Run Tests✅
  public String computeStats(List<Employee> employees) {

    double totalConsultantSalary = employees.stream().filter(Employee::consultant).mapToDouble(Employee::salary).sum();

    List<Integer> employeeIds = employees.stream().map(Employee::id).toList();


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

