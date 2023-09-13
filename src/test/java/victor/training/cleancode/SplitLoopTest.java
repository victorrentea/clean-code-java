package victor.training.cleancode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SplitLoopTest {

   @Test
   public void characterizationTest() {
      String actual = new SplitLoop().computeStats(asList(
          new Employee(1, 24, 2000, false),
          new Employee(1, 27, 2000, false),
          new Employee(2, 28, 1500, true),
          new Employee(3, 30, 2500, true)));
      assertEquals("Average age = 25; Average salary = 2000.0", actual);
   }


   @Nested
   @ExtendWith(MockitoExtension.class)
   class Hard {
      @Mock
      EmployeeService employeeService;
      @InjectMocks
      SplitLoop splitLoop;

      Employee consultant = new Employee(1, 24, 2000, true);
      Employee employee = new Employee(2, 30, 4000, false);

      @Test
      public void consultantIdNull() {
         consultant.setId(null);

         String actual = splitLoop.computeStatsHard(asList(consultant));

         assertEquals("Employee(s) not persisted", actual);
      }

      @Test
      public void throws_consultantSalaryNull_retrieveNull() {
         consultant.setSalary(null);
         when(employeeService.retrieveSalary(consultant.getId())).thenReturn(null);

         assertThrows(RuntimeException.class, () ->
             splitLoop.computeStatsHard(asList(consultant)));
      }

      @Test
      public void consultantSalaryNull_retrieveSalary_set() {
         consultant.setSalary(null);
         when(employeeService.retrieveSalary(consultant.getId())).thenReturn(3000);

         splitLoop.computeStatsHard(asList(consultant));


         assertThat(consultant.getSalary()).isEqualTo(3000);
      }

      @Test
      public void consultantSalaryNull_retrieveSalary_avg() {
         consultant.setSalary(null);
         when(employeeService.retrieveSalary(consultant.getId())).thenReturn(3000);
         Employee consultant2 = new Employee(15, 56, 5000, true);

         String actual = splitLoop.computeStatsHard(asList(consultant, consultant2));

         assertThat(actual).isEqualTo("Average employee age = 0; Average consultant salary = 4000.0");
      }
      @Test
      public void employeeAgeAverage() {
         Employee employee2 = new Employee(15, 56, 2000, false);

         String actual = splitLoop.computeStatsHard(asList(employee, employee2));

         assertThat(actual).isEqualTo("Average employee age = 43; Average consultant salary = 0.0");
      }
   }
}
