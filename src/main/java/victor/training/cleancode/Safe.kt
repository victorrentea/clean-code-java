package victor.training.cleancode

class Safe {
}

/**
 *
 */
class Player(
    val name: String,
    private var place:Int = 0
    ) {
    fun place() = place
    fun advance(roll: Int) {
        place += roll
        place %= 12
    }

    override fun toString(): String {
        return name
    }
}
data class PlayerImmutable(
    val name: String,
    val place:Int = 0
    ) {
    fun place() = place
    fun advance(roll: Int) = copy(place = (place + roll) % 12)

    override fun toString(): String {
        return name
    }
}

fun omg() {
    val player = Player("name")
    println(player.place())

}