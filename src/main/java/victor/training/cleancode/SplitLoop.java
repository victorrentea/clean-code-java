package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        boolean consultantFoundWithoutId = employees.stream().filter(Employee::isConsultant).anyMatch(e -> e.getId() == null);
        if (consultantFoundWithoutId) {
            return "Employee(s) not persisted";
        }

        long totalEmpAge = employees.stream()
                .filter(e -> !e.isConsultant())
                .mapToInt(Employee::getAge)
                .sum();

        updateConsultantSalaries(employees);
        double totalConsultantSalary = employees.stream()
                .filter(Employee::isConsultant)
                .mapToDouble(Employee::getSalary)
                .sum();

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

    private void updateConsultantSalaries(List<Employee> employees) {
        for (Employee employee : employees) {
            if (employee.isConsultant()) {
                if (employee.getSalary() == null) {
                    Integer salary = retrieveSallary(employee);
                    employee.setSalary(salary);
                }
            }
        }
        // employees.stream()
        //            .filter(Employee::isConsultant)
        //            .filter(employee -> employee.getSalary() == null)
        //            .forEach(employee -> employee.setSalary(retrieveSallary(employee)));
    }

    @NotNull
    private Integer retrieveSallary(Employee employee) {
        Integer salary = employeeService.retrieveSalary(employee.getId());
        if (salary == null) {
            throw new RuntimeException("NO salary found for employee " + employee.getId());
        }
        return salary;
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