package victor.training.cleancode

import org.jooq.lambda.tuple.Tuple
import org.jooq.lambda.tuple.Tuple2
import org.junit.jupiter.api.Test
import java.util.stream.Collectors

//@JvmInline
//value class CId(val id: Long) {}

class MicroTypes {

    //<editor-fold desc="Unknown source of data">
    fun extremeFP(): Map<Long, List<Pair<String, Int>>> {
        val customerId = 1L
        val product1Count = 2
        val product2Count = 4
        return mapOf(
            customerId to listOf(
                "Table" to product1Count,
                "Chair" to product2Count)
        )
    }

    //</editor-fold>

    @Test
    fun lackOfAbstractions() {
        val map = extremeFP()
        // Joke: try "var" above :)
        for (cid in map.keys) {
            val pl = map[cid]!!
                .map { (p,i) -> "$i of $p"}
                .joinToString(", ")
            println("cid=$cid got $pl")
        }
    }
}