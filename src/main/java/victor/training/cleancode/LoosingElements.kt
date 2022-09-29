package victor.training.cleancode




data class Child(var name:String)

fun main() {
    val childPoc = Child("Emma")
    val children = mapOf(childPoc to 8, Child("Vlad") to 4)
    val childrenSet = setOf(childPoc, Child("Vlad"))

    println("is my child with me ? " + (childPoc in children.keys))
    println(childPoc.hashCode())
    // adolescence
    childPoc.name = "Emma-Simona"

    println(childPoc.hashCode())
    println("is my child with me ? " + (childPoc in children.keys))
    println("Age = " + children[childPoc])

}