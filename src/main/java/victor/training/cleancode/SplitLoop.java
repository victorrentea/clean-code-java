package victor.training.cleancode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {
  // see tests
  public String computeStats(List<Employee> employees) {
    long averageAge = employees.stream()
        .filter(not(Employee::consultant))
        .mapToLong(Employee::age)
        .sum();

    List<Integer> consultantIds = employees.stream()
        .filter(Employee::consultant)
        .map(Employee::id)
//        .collect(Collectors.toList()); // java <17 naspa ca da ArrayList mutabil inapoi
        .toList();


    double averageSalary = employees
        .stream()
        .mapToDouble(Employee::salary)
        .average()
        .orElse(0);

    averageAge = averageAge / employees.stream().filter(e -> !e.consultant()).count();
//    averageSalary = averageSalary / employees.size();
    System.out.println("Consultant IDs: " + consultantIds);
    return "Average age = " + averageAge + "; Average salary = " + averageSalary;
  }


  // ======= hard core =========

  EmployeeService employeeService;

  public String computeStatsHard(List<Employee> employees) {
    long totalEmpAge = 0;
    double totalConsultantSalary = 0;
    for (Employee employee : employees) {
      if (!employee.consultant()) {
        totalEmpAge += employee.age();
        continue;
      }
      if (employee.id() == null) {
        return "Employee(s) not persisted";
      }

      Integer salary = employee.salary();
      if (salary == null) {
        salary = employeeService.retrieveSalary(employee.id());
        if (salary == null) {
          throw new RuntimeException("NO salary found for employee " + employee.id());
        }
      }
      totalConsultantSalary += salary;
    }

    long averageAge = 0;
    if (totalEmpAge != 0) {
      averageAge = totalEmpAge / employees.stream().filter(e -> !e.consultant()).count();
    }
    double averageConsultantSalary = 0;
    if (totalConsultantSalary != 0) {
      averageConsultantSalary = totalConsultantSalary / employees.size();
    }
    return "Average employee age = " + averageAge + "; Average consultant salary = " + averageConsultantSalary;
  }


}

interface EmployeeService {
  Integer retrieveSalary(int employeeId);
}

@Builder(toBuilder = true)
record Employee(Integer id, int age, Integer salary, boolean consultant) {
}