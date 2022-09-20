package victor.training.cleancode

fun f(n: Int) {
    println("Logic F")
    for (i in 0..3) {
        if (n + i < 0) {
            println("Code $i")
        } else {
            throw IllegalArgumentException()
        }
    }
}

fun g(n: Int) {
    println("Logic G")
    try {
        for (j in 0..3) {
            if (n + j < 0) {
                println("Code$j")
            } else {
                throw IllegalArgumentException()
            }
        }
    } catch (e: Exception) {
        throw RuntimeException("Rethrow", e)
    }
}