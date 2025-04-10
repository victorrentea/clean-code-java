package victor.training.cleancode;

import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

public class SplitLoop {
  // TODO Split loops and refactor to .stream. Run Tests✅
  public String computeStats(List<Employee> employees) {
    // A
    //    double totalConsultantSalary = 0;
//    for (Employee employee : employees) {
//      if (employee.consultant()) {
//        totalConsultantSalary += employee.salary();
//      }
//    }
    // B
    double totalConsultantSalary = employees.stream()
        .filter(Employee::consultant)
        .mapToDouble(Employee::salary)
        .sum();
    // TS const totalConsultantSalary = employees.filter(e => e.consultant()).map(e=>e.salary).reduce(0, (a,b)=>a+b);
    // PY totalConsultantSalary = sum(e.salary for e in employees if e.consultant())
    // C# totalConsultantSalary = employees.Where(e => e.consultant()).Sum(e => e.salary);

    List<Integer> employeeIds = employees.stream().map(Employee::id).collect(Collectors.toList());

//    List<Integer> employeeIds = employees.stream().map(Employee::id).toList();
//    TS const employeeIds = employees.map(e=>e.id);
//    PY employeeIds = [e.id for e in employees]
//    C# employeeIds = employees.Select(e => e.id).ToList();

//    C# employeeIds = []
//    C# employeeIds = employees.ForEach(e => list.Add(id));

    System.out.println("Employee IDs: " + employeeIds);
    return "Total consultant salary: " + totalConsultantSalary + "; ids: " + employeeIds;
  }

  @Builder(toBuilder = true)
  public record Employee(
      Integer id,
      int age,
      Integer salary,
      boolean consultant) {
  }
}

