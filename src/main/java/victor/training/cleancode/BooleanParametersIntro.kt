package victor.training.cleancode

fun main() {
    // The big method is called from various foreign places in the codebase
    bigUglyMethod(1, Task(5))
    bigUglyMethod(2, Task(4))
    bigUglyMethod(3, Task(3))
    bigUglyMethod(4, Task(2))
    bigUglyMethod(5, Task(1))

    // TODO From my CR #323, I call it too, to do more within:
    val task = Task(1)
    bigUglyMethod(2, task, false)

}



fun bigUglyMethod(b: Int, task: Task, cr323: Boolean = false) {
    println("Complex Logic 1 $task and $b")
    println("Complex Logic 2 $task")
    println("Complex Logic 3 $task")

    if (cr323) {
        println("Logic just for CR#323 : $task");
    }

    println("More Complex Logic $b")
    println("More Complex Logic $b")
    println("More Complex Logic $b")
}



class Task(val id: Int) {
    var isStarted = false
        private set

    fun setStarted() {
        isStarted = true
    }

    override fun toString(): String {
        return "Task{" + "id=" + id + ", started=" + isStarted + '}'
    }
}