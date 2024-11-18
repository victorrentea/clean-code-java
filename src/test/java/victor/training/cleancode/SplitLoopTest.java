package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.SplitLoop.Employee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitLoopTest {

    @Test
    void computesCorrectAverageSalaryAndTotalConsultantSalary() {
        List<Employee> employees = List.of(
            Employee.builder().id(1).age(24).salary(2000).consultant(false).build(),
            Employee.builder().id(2).age(27).salary(2000).consultant(false).build(),
            Employee.builder().id(3).age(28).salary(1500).consultant(true).build(),
            Employee.builder().id(4).age(30).salary(2500).consultant(true).build()
        );

        String result = new SplitLoop().computeStats(employees);

        assertEquals("Total consultant salary: 4000.0; ids: [1, 2, 3, 4]", result);
    }

    @Test
    void returnsZeroForEmptyEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        String result = new SplitLoop().computeStats(employees);

        assertEquals("Total consultant salary: 0.0; ids: []", result);
    }

    @Test
    void computesCorrectlyWithAllConsultants() {
        List<Employee> employees = List.of(
            Employee.builder().id(1).age(24).salary(3000).consultant(true).build(),
            Employee.builder().id(2).age(27).salary(4000).consultant(true).build()
        );

        String result = new SplitLoop().computeStats(employees);

        assertEquals("Total consultant salary: 7000.0; ids: [1, 2]", result);
    }

    @Test
    void computesCorrectlyWithNoConsultants() {
        List<Employee> employees = List.of(
            Employee.builder().id(1).age(24).salary(2000).consultant(false).build(),
            Employee.builder().id(2).age(27).salary(2500).consultant(false).build()
        );

        String result = new SplitLoop().computeStats(employees);

        assertEquals("Total consultant salary: 0.0; ids: [1, 2]", result);
    }

}
