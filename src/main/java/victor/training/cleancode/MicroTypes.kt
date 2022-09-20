package victor.training.cleancode

import org.jooq.lambda.tuple.Tuple
import org.jooq.lambda.tuple.Tuple2
import org.junit.jupiter.api.Test
import java.util.stream.Collectors

//@JvmInline
//value class CId(val id: Long) {}

class MicroTypes {


    //<editor-fold desc="Unknown source of data">
    fun extremeFP(): Map<Long, List<Tuple2<String, Int>>> {
        val customerId = 1L
        val product1Count = 2
        val product2Count = 4
        return java.util.Map.of(
            customerId, java.util.List.of(
                Tuple.tuple("Table", product1Count),
                Tuple.tuple("Chair", product2Count)
            )
        )
    }

    //</editor-fold>

    @Test
    fun lackOfAbstractions() {
        val map = extremeFP()
        // Joke: try "var" above :)
        for (cid in map.keys) {
            val pl = map[cid]!!
                .map { t: Tuple2<String, Int> -> t.v2.toString() + " of " + t.v1 }
                .joinToString(", ")
            println("cid=$cid got $pl")
        }
    }
}