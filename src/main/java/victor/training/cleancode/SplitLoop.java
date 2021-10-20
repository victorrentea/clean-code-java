package victor.training.cleancode;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.support.Employee;
import victor.training.cleancode.support.EmployeeService;
import victor.training.cleancode.support.User;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Value
class X {
   int x;
   String s;
   ImmutableList<String> list;
}

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
@RequiredArgsConstructor
public class SplitLoop {
   private final EmployeeService employeeService;

   public String computeStatsHard(List<Employee> employees) throws IOException {

//      Function<User, User> f1;
//      Function<User, String> f2;
//
//      Function<User, String> userStringFunction = f1.andThen(f2);
//      Stream<User> s = Files.lines(new File("huge1PB.csv").toPath());
//      s.map(f1).map(f2).forEach(repo::save);

      String brilliant = "it is easier to optimize clean code than to clean optimized code";

//      Writer writer;

//      Unchecked.consumer(writer::write)

//      What do you think about passing extracted method from inside for loop as a callback from HOC?


      /// What if you put complex logic in small functions and run them through a
      // pipe instead of  grouping in before and after.
      // This will even allow to change order or put custom logic in different place than in the middle?

      long averageAge = employees.stream().filter(e -> !e.isConsultant()).mapToLong(Employee::getAge).sum();

      List<Employee> consultants = employees.stream().filter(Employee::isConsultant).collect(Collectors.toList());
      if (consultants.stream().anyMatch(c -> c.getId() == null)) {
         return "Employee(s) not persisted";
      }

      for (Employee employee : consultants) {
         if (employee.getSalary() == null) {
            Integer salary = retrieveSallary(employee);
            employee.setSalary(salary);
         }
      }

      double averageSalary = consultants.stream().mapToDouble(Employee::getSalary).average().getAsDouble();

      averageAge = averageAge / employees.stream().filter(e -> !e.isConsultant()).count();
      return "Agerage age = " + averageAge + "; Internal employee average salary = " + averageSalary;
   }

   private Integer retrieveSallary(Employee employee) {
      Integer salary = employeeService.retrieveSalary(employee.getId());
      if (salary == null) {
         throw new RuntimeException("NO salary found for employee " + employee.getId());
      }
      return salary;
   }
}
