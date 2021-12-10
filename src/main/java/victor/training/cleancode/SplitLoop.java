package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {

    // ======= hard core =========

    EmployeeService employeeService;

    public String computeStatsHard(List<Employee> employees) {
        if (employees.isEmpty()) {
            return "N/A";
        }
        long totalEmpAge;
        double averageConsultantSalary = 0;
        for (Employee employee : employees) {
            if (employee.isConsultant()) {
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
                averageConsultantSalary += employee.getSalary();
            }
        }
        totalEmpAge = employees.stream().filter(employee -> !employee.isConsultant()).mapToLong(Employee::getAge).sum();

        long averageAge = 0;
        if (totalEmpAge != 0) {
            averageAge = totalEmpAge / employees.stream().filter(e -> !e.isConsultant()).count();
        }
        if (employees.size() != 0) {
            averageConsultantSalary /= employees.size();
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