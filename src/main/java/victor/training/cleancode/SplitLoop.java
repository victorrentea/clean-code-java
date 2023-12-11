package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.function.Predicate.not;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {

  // see tests
  public String computeStats(List<Employee> employees) {
    long averageAge = 0;
    double averageSalary = 0;
    List<Integer> consultantIds = new ArrayList<>();
    for (Employee employee : employees) {
      if (!employee.isConsultant()) {
        averageAge += employee.getAge();
      } else {
        consultantIds.add(employee.getId());
      }
      averageSalary += employee.getSalary();
    }
    averageAge = averageAge / employees.stream().filter(e -> !e.isConsultant()).count();
    averageSalary = averageSalary / employees.size();
    System.out.println("Consultant IDs: " + consultantIds);
    return "Average age = " + averageAge + "; Average salary = " + averageSalary;
  }


  // ======= hard core =========

  EmployeeService employeeService;

  public String computeStatsHard(List<Employee> employees) {
    double averageAge = employees.stream()
        .filter(not(Employee::isConsultant)) // ca sa folosim :: 💖
        .mapToInt(Employee::getAge) // IntStream (ala are .sum)
        .average()
        .orElse(0);
//    double averageAge = 0;
//    if (totalEmpAge != 0) {
//      averageAge = totalEmpAge / consultants.size();
//    }
    List<Employee> consultants = employees.stream().filter(Employee::isConsultant).toList();

    if (consultants.stream().anyMatch(e -> e.getId() == null)) {
      return "Employee(s) not persisted";
    }

    retrieveSallaries(consultants);

    double averageConsultantSalary = consultants.stream()
        .mapToDouble(Employee::getSalary)
        .average()
        .orElse(0);

    return "Average employee age = " + averageAge + "; Average consultant salary = " + averageConsultantSalary;
  }

  private void retrieveSallaries(List<Employee> consultants) {
    for (Employee consultant : consultants) {
      if (consultant.getSalary() == null) {
        Integer salary = employeeService.retrieveSalary(consultant.getId());
        if (salary == null) {
          throw new RuntimeException("NO salary found for employee " + consultant.getId());
        } else {
          consultant.setSalary(salary);
        }
      }
    }
  }


}

interface EmployeeService {
  Integer retrieveSalary(int employeeId);
}

@Data
@AllArgsConstructor
class Employee {
  private Integer id;
  private int age;
  private Integer salary;
  private boolean consultant;
}