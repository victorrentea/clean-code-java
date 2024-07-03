package victor.training.cleancode;

import lombok.Builder;

import java.util.List;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {
  // run tests
  public String computeStats(List<Employee> employees) {
    // variab acumulator
    double averageAge = employees.stream()
        .filter(employee -> !employee.consultant())
        .mapToLong(Employee::age)
        .average()
        .orElseThrow();

    List<Integer> consultantIds = employees.stream()
        .filter(Employee::consultant)
        .map(Employee::id)
        .toList();
    //    List<Integer> consultantIds = employees.stream()
//        .filter(Employee::consultant)
//        .map(Employee::id)
////        .collect(Collectors.toList()); // java 8
//        .toList(); // java 17
    double averageSalary = employees.stream().mapToDouble(Employee::salary).average().orElseThrow();

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