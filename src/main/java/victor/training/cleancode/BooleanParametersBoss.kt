package victor.training.cleancode

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
class BooleanParametersBoss {
    // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
    // Lord gave us tests!
    fun bossLevel(fluff: Boolean, tasks: List<Task>, cr323: Boolean) {
        var index = 0 // TODO move closer to usages
        val j = tasks.size
        println("Logic1")
        val taskIds: MutableList<Int> = ArrayList()
        println("Logic2")
        if (fluff) {
            println("Logic3")
            for (task in tasks) {
                println("Logic4: Validate $task")
                task.setStarted()
                taskIds.add(task.id)
                if (cr323) { // TODO remove the boolean
                    println("My Logic: $task")
                }
                index++
                println("Audit task index=$index: $task")
            }
            println("Logic6 $j")
            println("Task Ids: $taskIds")
        } else {
            println("Logic7 $tasks")
        }
        println("Logic7")
    }

}