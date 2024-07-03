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
    long totalAge = employees.stream().filter(employee -> !employee.consultant()).mapToLong(Employee::age).sum();

    List<Integer> consultantIds = employees.stream()
        .filter(Employee::consultant)
        .map(Employee::id)
        .toList();
    //    List<Integer> consultantIds = employees.stream()
//        .filter(Employee::consultant)
//        .map(Employee::id)
////        .collect(Collectors.toList()); // java 8
//        .toList(); // java 17

    double averageSalary = employees.stream().mapToDouble(Employee::salary).sum();

    System.out.println("Average age = " + totalAge); // codu minte aici!! variabila are doua semnificatii
    long averageAge = totalAge / employees.stream().filter(e -> !e.consultant()).count();
    averageSalary /= employees.size();
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