package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class SplitLoop {

  public String computeStats(List<Employee> employees) {
    List<Integer> employeeIds = new ArrayList<>();
    double totalConsultantSalary = 0;
    for (Employee employee : employees) { // SRP: sa faci 1 lucru odata
      if (employee.consultant()) {
        totalConsultantSalary += employee.salary();
      }
    }
    for (Employee employee : employees) { // SRP: sa faci 1 lucru odata
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

