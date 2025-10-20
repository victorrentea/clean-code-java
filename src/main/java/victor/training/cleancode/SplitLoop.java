package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SplitLoop {

  public String computeStats(List<Employee> employees) {
    double totalConsultantSalary = employees.stream()
        .filter(Employee::consultant)
        .mapToDouble(Employee::salary) // 💩
        .sum();
    // TS = employees.filter(e=>e.consultant).reduce(0, (acc,e)=>acc+e.salary)
    // PY = sum(e.salary for e in employees if e.consultant)

    // SRP: sa faci 1 lucru odata
    List<Integer> employeeIds = employees.stream().map(Employee::id).toList();
    // TS: = employees.map(e=>e.id)
    // PY: = [e.id for e in employees]
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

