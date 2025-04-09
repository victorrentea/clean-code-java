package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class SplitLoop {
  // TODO Split loops and refactor to .stream. Run Tests✅
  public String computeStats(List<Employee> employees) {
    List<Integer> employeeIds = new ArrayList<>();
    double totalConsultantSalary = 0;
    for (Employee employee : employees) {
      if (employee.consultant()) {
        totalConsultantSalary += employee.salary();
      }
    }
    for (Employee employee : employees) {
      employeeIds.add(employee.id());
    }
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

