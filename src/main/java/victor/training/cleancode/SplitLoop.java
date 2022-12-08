
package victor.training.cleancode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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

        long totalAge = employees.stream()
                .filter(not(Employee::isConsultant))
                .mapToLong(Employee::getAge)
                .sum();
        long averageAge = totalAge / employees.stream().filter(e -> !e.isConsultant()).count();

        double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
        double averageSalary = totalSalary / employees.size();

        List<Integer> consultantIds = employees.stream().filter(Employee::isConsultant).map(Employee::getId).toList();
        System.out.println("Consultant IDs: " + consultantIds);
        return "Average age = " + averageAge + "; Average salary = " + averageSalary;
    }


    // ======= hard core =========

    EmployeeService employeeService;

    public String computeStatsHard(List<Employee> employees) {
        long totalEmpAge = 0;
        double totalConsultantSalary = 0;
        for (Employee employee : employees) {
            if (!employee.isConsultant()) {
                totalEmpAge += employee.getAge();
                continue;
            }
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