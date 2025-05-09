package victor.training.cleancode.fp

fun main() {
    val list50K = (1..50_000).toList()

    val rez = list50K.fold(listOf<Int>())
    { list, e -> list + e * e }
    println(rez.size)

    val rezFP = list50K.map { it * it }
    println(rez.size)
}