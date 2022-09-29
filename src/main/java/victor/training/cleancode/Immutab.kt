package victor.training.cleancode

data class Immutab(
    val list:List<String>,
    val st12:St12,
    val in1:Int,
    val in3:Int,
    val in2:Int,
    ) {
}
data class St12( val st1:String,
                 val st2:String,) {
}

fun f(i:Immutab): Immutab {
   return i.copy(
       st12= St12("newS1","newS2"),
   )
}
data class AB(val a:Int, val b:Int) {
    operator fun plus(ab: AB) = AB(a+ab.a, b+ab.b)
}
fun sumPairs(strings:List<String>) {

    strings.fold(AB(0,0)) { old, s ->
        val newA = s.split("\\s".toRegex())[0].toInt()
        val newB = s.split("\\s".toRegex())[1].toInt()
        old + AB(newA, newB) }


}
fun f0(list:List<Map<Int, Double>>) {
    // a Map<Int, Double> adding all doubles for the same int
    val x: Map<Int, Double> = list.flatMap { it.entries }
        .groupBy { it.key }
        .mapValues { it.value.map { it.value }.sum() }

    val m = mutableMapOf<Int,Double>()
}
//shallow/deep immutability


// functional core / imperative shell ?