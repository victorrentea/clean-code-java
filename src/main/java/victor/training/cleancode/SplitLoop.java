package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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
        List<Employee> consultants = employees.stream().filter(Employee::isConsultant).collect(toList());
        // validare
        if (consultants.stream().anyMatch(c -> c.getId() == null)) {
            return "Employee(s) not persisted";
        }

        // data enhancement/ retrieval
        retrieveSalaries(consultants);

        // calcule
        long totalAge = employees.stream().filter(employee -> !employee.isConsultant()).mapToLong(Employee::getAge).sum();
        double totalSalary = consultants.stream().mapToDouble(Employee::getSalary).sum();
        totalAge = totalAge / employees.stream().filter(e -> !e.isConsultant()).count();
        totalSalary = totalSalary / employees.size();
        return "Agerage age = " + totalAge + "; Internal employee average salary = " + totalSalary;
    }

    private void retrieveSalaries(List<Employee> consultants) {
        for (Employee employee : consultants) {
            if (employee.getSalary() == null) {
                Integer salary = employeeService.retrieveSalary(employee.getId())
                    .orElseThrow(() -> new RuntimeException("NO salary found for employee " + employee.getId()));
                employee.setSalary(salary);
            }
        }
    }

    EmployeeService employeeService;

}
interface EmployeeService {
    Optional<Integer> retrieveSalary(int employeeId);
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