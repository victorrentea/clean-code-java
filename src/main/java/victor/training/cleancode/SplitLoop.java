package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {

    // see tests
    public String computeStats(List<Employee> employees) {
        long total = 0;
        double averageSalary = 0;
        List<Integer> consultantIds = new ArrayList<>();
        for (Employee employee : employees) {
            if (!employee.isConsultant()) {
                total += employee.getAge();
            } else {
                consultantIds.add(employee.getId());
            }
            averageSalary += employee.getSalary();
        }
        long average = total / employees.stream().filter(e -> !e.isConsultant()).count();
        averageSalary = averageSalary / employees.size();
        System.out.println("Consultant IDs: " + consultantIds);
        return "Average age = " + total + "; Average salary = " + averageSalary;
    }


    // ======= hard core =========

    EmployeeService employeeService;

    public String computeStatsHard(List<Employee> employees) {



        if (employees.stream().filter(Employee::isConsultant).anyMatch(employee -> employee.getId() == null)) {
            return "Employee(s) not persisted";
        }
        for (Employee employee : employees) {
            if (employee.isConsultant()) {
                if (employee.getSalary() == null) {
                    Integer salary = retrieveSalary(employee.getId());
                    employee.setSalary(salary);
                }
            }
        }
        double totalConsultantSalary = employees.stream().filter(Employee::isConsultant).mapToDouble(Employee::getSalary).sum();

        long averageAge = (long) employees.stream()
            .filter(not(Employee::isConsultant))
            .mapToLong(Employee::getAge)
            .average()
            .orElse(0);

        double averageConsultantSalary = 0;
        if (totalConsultantSalary != 0) {
            averageConsultantSalary = totalConsultantSalary / employees.size();
        }
        return "Average employee age = " + averageAge + "; Average consultant salary = " + averageConsultantSalary;
    }

    private Integer retrieveSalary(Integer id) {
        Integer salary = employeeService.retrieveSalary(id);
        if (salary == null) {
            throw new RuntimeException("NO salary found for employee " + id);
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