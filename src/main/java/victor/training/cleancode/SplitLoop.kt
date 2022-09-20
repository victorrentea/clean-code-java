package victor.training.cleancode

import lombok.AllArgsConstructor
import lombok.Data

/**
 * Break the loops and refactor to use .stream to compute stuff.
 */
class SplitLoop {
    // see tests
    fun computeStats(employees: List<Employee>): String {
        var averageAge: Long = 0
        var averageSalary = 0.0
        val consultantIds: MutableList<Int> = ArrayList()
        for (employee in employees) {
            if (!employee.consultant) {
                averageAge += employee.age.toLong()
            } else {
                employee.id?.let { consultantIds.add(it) }
            }
            averageSalary += (employee.salary ?: 0)
        }
        averageAge /= employees.count { e: Employee -> !e.consultant }
        averageSalary /= employees.size
        println("Consultant IDs: $consultantIds")
        return "Average age = $averageAge; Average salary = $averageSalary"
    }

    // ======= hard core =========
    private var employeeService: EmployeeService? = null
    fun computeStatsHard(employees: List<Employee>): String {
        var totalEmpAge: Long = 0
        var totalConsultantSalary = 0.0
        for (employee in employees) {
            if (!employee.consultant) {
                totalEmpAge += employee.age.toLong()
                continue
            }
            if (employee.id == null) {
                return "Employee(s) not persisted"
            }
            if (employee.salary == null) {
                val salary = employeeService!!.retrieveSalary(employee.id!!)
                if (salary == null) {
                    throw RuntimeException("NO salary found for employee " + employee.id)
                } else {
                    employee.salary= salary
                }
            }
            totalConsultantSalary += employee.salary!!.toDouble()
        }
        var averageAge: Long = 0
        if (totalEmpAge != 0L) {
            averageAge = totalEmpAge / employees.stream().filter { e: Employee -> !e.consultant }.count()
        }
        var averageConsultantSalary = 0.0
        if (totalConsultantSalary != 0.0) {
            averageConsultantSalary = totalConsultantSalary / employees.size
        }
        return "Average employee age = $averageAge; Average consultant salary = $averageConsultantSalary"
    }
}

interface EmployeeService {
    fun retrieveSalary(employeeId: Int): Int?
}

@Data
@AllArgsConstructor
data class Employee(
    var id: Int? = null,
    val age:Int = 0,
    var salary: Int? = null,
    val consultant:Boolean = false) {
}