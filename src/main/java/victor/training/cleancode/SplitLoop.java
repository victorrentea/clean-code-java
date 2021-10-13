package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

@Slf4j
/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {

    // see tests
    public String computeStats(List<Employee> employees) {
        double averageSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0);

        int averageAge = (int) employees.stream()

            .filter(e -> !e.isConsultant())
            .filter(not(Employee::isConsultant))
            .filter(Employee::isNotConsultant)

            .mapToLong(Employee::getAge)
            .average()
            .orElse(0);

        List<Integer> consultantsIds = employees.stream()
            .filter(Employee::isConsultant)
            .map(Employee::getId)
            .collect(toList());

        System.out.println(consultantsIds);
        return "avg age = " + averageAge + "; avg sal = " + averageSalary;
    }


    // ======= hard core =========

    public String computeStatsHard(List<Employee> employees) {
        long averageAge = 0;
        double averageSalary = 0;
        for (Employee employee : employees) {
            if (!employee.isConsultant()) {
                averageAge += employee.getAge();
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
            averageSalary += employee.getSalary();
        }
        averageAge = averageAge / employees.stream().filter(e -> !e.isConsultant()).count();
        averageSalary = averageSalary / employees.size();
        return "Agerage age = " + averageAge + "; Internal employee average salary = " + averageSalary;
    }

    EmployeeService employeeService;

}
interface EmployeeService {
    Integer retrieveSalary(int employeeId);
}

@Data
@AllArgsConstructor
class Employee {
    private final Integer id;
    private final int age;
    private Integer salary;
    private final boolean consultant;

    public boolean isNotConsultant() {
        return !consultant;
    }
}

//class Consultant extends Employee {}