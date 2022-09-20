package victor.training.cleancode

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
class BooleanParametersBoss {
    // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
    // Lord gave us tests!
    fun bossLevelFluff(tasks: List<Task>) {
        f1(tasks)
        f2(tasks)
    }
    fun bossLevelFluff323(tasks: List<Task>) {
        f1(tasks)
        for (task in tasks) {
            println("My Logic: $task")
        }
        f2(tasks)
    }

    private fun f2(tasks: List<Task>) {
        var index = 0
        for (task in tasks) {
            index++
            println("Audit task index=$index: $task")
        }
        println("Logic6 ${tasks.size}")
        val taskIds = tasks.map { it.id }
        println("Task Ids: $taskIds")
        println("Logic7")
    }

    private fun f1(tasks: List<Task>) {
        println("Logic1")
        println("Logic2")
        println("Logic3")
        for (task in tasks) {
            println("Logic4: Validate $task")
            task.setStarted()
        }
    }

    fun bossLevelNoFluff(tasks: List<Task>) {
        println("Logic1")
        println("Logic2")
        println("Logic7 $tasks")
        println("Logic7")
    }

}