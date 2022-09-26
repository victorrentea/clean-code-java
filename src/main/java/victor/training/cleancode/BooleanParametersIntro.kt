package victor.training.cleancode

fun main() {
    // The big method is called from various foreign places in the codebase
    bigUglyMethod(1, Task(5))

    bigUglyMethod(2, Task(4))

    bigUglyMethod(3, Task(3))

    bigUglyMethod(4, Task(2))

    bigUglyMethod(5, Task(1))
    bigUglyWithHistory(CustomerHistory())



    // TODO From my CR #323, I call it too, to do more within:
    val task = Task(1)
    bigUglyMethod323(2, task)


    // TODO From my CR #370, I call it too, to do more within:
//    val task = Task(1)
//    bigUglyMethod(2, task, true)
}

fun bigUglyMethod(b2: Int, task: Task) {
    bigUglyStart(task, b2)
    bigUglyEnd(b2)
}

private fun bigUglyWithHistory(it: CustomerHistory) {
    println("Stuff with $it")
}

fun bigUglyMethod323(b2: Int, task: Task) {
    bigUglyStart(task, b2)
    System.out.println("Logic just for CR#323 : " + task);
    System.out.println("Logic just for CR#323 : " + task);
    System.out.println("Logic just for CR#323 : " + task);
    bigUglyEnd(b2)
}

class CustomerHistory


private fun bigUglyEnd(b2: Int) {
    println("More Complex Logic $b2")
    println("More Complex Logic $b2")
    println("More Complex Logic $b2")
    println("More Complex Logic $b2")
    println("More Complex Logic $b2")
}

private fun bigUglyStart(task: Task, b2: Int) {
    println("Complex Logic 1 $task and $b2")
    println("Complex Logic 2 $task")
    println("Complex Logic 3 $task")
    println("Complex Logic 3 $task")
}
