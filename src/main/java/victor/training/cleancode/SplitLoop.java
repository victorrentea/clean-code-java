package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

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

        List<Employee> consultants = employees.stream().filter(Employee::isConsultant).collect(toList());

        if (consultants.stream().anyMatch(e -> e.getId() == null)) {
            return "Employee(s) not persisted";
        }

        long totalEmpAge = employees.stream()
            .filter(not(Employee::isConsultant))
            .mapToLong(Employee::getAge)
            .sum();

        for (Employee consultant : consultants) {
            if (consultant.getSalary() == null) {
                consultant.setSalary(retrieveSallary(consultant));
            }
        }

        double totalConsultantSalary = consultants.stream().mapToDouble(Employee::getSalary).sum();

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

    private Integer retrieveSallary(Employee consultant) {
        Integer salary = employeeService.retrieveSalary(consultant.getId());
        if (salary == null) {
            throw new RuntimeException("NO salary found for employee " + consultant.getId());
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