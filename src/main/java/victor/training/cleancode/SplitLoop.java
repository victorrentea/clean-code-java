package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


class DivuDeSus {
  public void method1() {

  }
}

class Pagina {
  @Delegate
  DivuDeSus divuDeSus;

  public static void method() {
    Pagina pagina = new Pagina();
    pagina.method1();
  }
}


@Data
class ObiectuMeuCaLaTara {
  private String a;
  private String b;
  private String c;

  public static void method() {
    //    new ObiectuMeuCaLaTara()
    //            .setA("a")
    //            .setB("b")
  }
}


/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {

  EmployeeService employeeService;

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

  public void moarta() {

  }


  // ======= hard core =========

  public void doarDeBlackFriday() {
    // moarta();
  }

  public String computeStatsHard(List<Employee> employees) {
    long totalEmpAge;
    double totalConsultantSalary = 0;
    long sum = employees.stream().filter(employee1 -> !employee1.isConsultant()).mapToLong(Employee::getAge).sum();
    totalEmpAge = sum;
    for (Employee employee : employees) {
      if (!!employee.isConsultant()) {
        if (employee.getId() == null) {
          return "Employee(s) not persisted";
        }
        if (employee.getSalary() == null) {
          Integer salary = employeeService.retrieveSalary(employee.getId());
          if (salary == null) {
            throw new RuntimeException("NO salary found for employee " + employee.getId());
          } else {
              employee.setSalary(salary);
            }
          }
          totalConsultantSalary += employee.getSalary();
        }
      }

        long averageAge = 0;
        if (totalEmpAge != 0) {
            averageAge = totalEmpAge / employees.stream().filter(e -> !e.isConsultant()).count();
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

@Data
@AllArgsConstructor
class Employee {
    private Integer id;
    private int age;
    private Integer salary;
    private boolean consultant;
}