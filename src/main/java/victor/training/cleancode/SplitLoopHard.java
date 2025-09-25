package victor.training.cleancode;

import victor.training.cleancode.SplitLoop.Employee;

import java.util.List;

public class SplitLoopHard {

  private EmployeeService employeeService;

    // TODO Split loops and refactor to .stream. Run Tests✅
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

    interface EmployeeService {
        Integer retrieveSalary(int employeeId);
    }
}
