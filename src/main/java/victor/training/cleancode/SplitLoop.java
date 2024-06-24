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
    long averageAge = 0;
    double averageSalary = 0;
    List<Integer> consultantIds = new ArrayList<>();
    for (Employee employee : employees) {
      if (!employee.consultant()) {
        averageAge += employee.age();
      } else {
        consultantIds.add(employee.id());
      }
      averageSalary += employee.salary();
    }
    averageAge = averageAge / employees.stream().filter(e -> !e.consultant()).count();
    averageSalary = averageSalary / employees.size();
    System.out.println("Consultant IDs: " + consultantIds);
    return "Average age = " + averageAge + "; Average salary = " + averageSalary;
  }
}

@Builder(toBuilder = true)
record Employee(
    Integer id,
    int age,
    Integer salary,
    boolean consultant) {
}