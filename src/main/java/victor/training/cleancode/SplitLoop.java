package victor.training.cleancode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

interface EmployeeService {
    Integer retrieveSalary(int employeeId);
}

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
public class SplitLoop {

    EmployeeService employeeService;


    // ======= hard core =========

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

    public String computeStatsHard(List<Employee> employees) {
        long totalEmpAge;
        double totalConsultantSalary = 0;
        totalEmpAge = employees.stream()
                .filter(employee -> !employee.isConsultant())
                .mapToLong(Employee::getAge)
                .sum();

//        employees.
//        .filter(lambda e:e.isConsultant)
//        .map(lamda e: e.getAget())
//        .sum
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

@Data
@AllArgsConstructor
class Employee {
    private Integer id;
    private int age;
    private Integer salary;
    private boolean consultant;
}