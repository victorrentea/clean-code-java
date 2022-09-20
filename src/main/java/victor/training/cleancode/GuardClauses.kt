package victor.training.cleancode

class GuardClauses {
    fun getPayAmount(marine: Marine?): Int {
        var result: Int
        if (marine != null) {
            if (!isDead(marine)) {
                if (!marine.isRetired) {
                    if (marine.getYearsService() != null) {
                        result = marine.getYearsService()!! * 100
                        if (marine.awards.isNotEmpty()) {
                            result += 1000
                        }
                        if (marine.awards.size >= 3) {
                            result += 2000
                        }
                        // HEAVY logic here...
                    } else {
                        throw IllegalArgumentException("Any marine should have the years of service set")
                    }
                } else result = retiredAmount()
            } else {
                result = DEAD_PAY_AMOUNT
            }
        } else {
            throw RuntimeException("Marine is null")
        }
        return result // TODO ALT-ENTER move return closer
    }

    private fun isDead(marine: Marine): Boolean {
        return false
    }

    private fun retiredAmount(): Int {
        return 2
    }

    companion object {
        const val DEAD_PAY_AMOUNT = 1
    }
}

class Marine(val isDead: Boolean, val isRetired: Boolean, private val yearsService: Int) {
    val awards: List<Award> = ArrayList()

    fun getYearsService(): Int? {
        return yearsService
    }
}

 class Award