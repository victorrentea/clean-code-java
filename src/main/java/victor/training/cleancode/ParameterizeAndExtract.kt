package victor.training.cleancode

fun f(n: Int) {
    println("Logic F")
    common(n)
}

fun g(n: Int) {
    println("Logic G")
    try {
        common(n)
    } catch (e: Exception) {
        throw RuntimeException("Rethrow", e)
    }
}

private fun common(n: Int) {
    for (i in 0..3) {
        if (n + i < 0) {
            println("Code $i")
        } else {
            throw IllegalArgumentException()
        }
    }
}