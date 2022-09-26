package victor.training.cleancode

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
class BooleanParameters {
    // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
    // Lord gave us tests!
    fun bossLevelFluff(tasks: List<Task>, cr323: Boolean) {
        bossStart(tasks)

        if (cr323) { // TODO remove the boolean
            for (task in tasks) {
                println("My Logic: $task")
            }
        }

        bossEnd(tasks)
    }

    private fun bossEnd(tasks: List<Task>) {
        tasks.forEachIndexed {index,task->println("Audit task index=$index: $task") }
        println("Logic6 ${tasks.size}")
        val taskIds = tasks.map { it.id }
        println("Task Ids: $taskIds")
        println("Logic7")
    }

    private fun bossStart(tasks: List<Task>) {
        println("Logic1")
        println("Logic2")
        println("Logic3")
        for (task in tasks) {
            println("Logic4: Validate $task")
            task.started = true
        }
    }

    fun bossLevelNoFluff(tasks: List<Task>) {
        println("Logic1")
        println("Logic2")
        println("Logic7 $tasks")
        println("Logic7")
    }

}

class Task(val id: Int, var started: Boolean = false) {

    override fun toString(): String {
        return "Task{" + "id=" + id + ", started=" + started + '}'
    }
}