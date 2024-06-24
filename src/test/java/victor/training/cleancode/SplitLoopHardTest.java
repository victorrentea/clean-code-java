package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SplitLoopHardTest {
  @Mock
  EmployeeService employeeService;
  @InjectMocks
  SplitLoopHard splitLoopHard;

  Employee consultant = new Employee(1, 24, 2000, true);
  Employee employee = new Employee(2, 30, 4000, false);

  @Test
  public void consultantIdNull() {
    consultant = consultant.toBuilder().id(null).build();

    String actual = splitLoopHard.computeStatsHard(asList(consultant));

    assertEquals("Employee(s) not persisted", actual);
  }

  @Test
  public void throws_consultantSalaryNull_retrieveNull() {
    consultant = consultant.toBuilder().salary(null).build();
    when(employeeService.retrieveSalary(consultant.id())).thenReturn(null);

    assertThrows(RuntimeException.class, () ->
        splitLoopHard.computeStatsHard(asList(consultant)));
  }

  @Test
  public void consultantSalaryNull_retrieveSalary_avg() {
    consultant = consultant.toBuilder().salary(null).build();
    when(employeeService.retrieveSalary(consultant.id())).thenReturn(3000);
    Employee consultant2 = new Employee(15, 56, 5000, true);

    String actual = splitLoopHard.computeStatsHard(asList(consultant, consultant2));

    assertThat(actual).isEqualTo("Average employee age = 0; Average consultant salary = 4000.0");
  }

  @Test
  public void employeeAgeAverage() {
    Employee employee2 = new Employee(15, 56, 2000, false);

    String actual = splitLoopHard.computeStatsHard(asList(employee, employee2));

    assertThat(actual).isEqualTo("Average employee age = 43; Average consultant salary = 0.0");
  }
}
