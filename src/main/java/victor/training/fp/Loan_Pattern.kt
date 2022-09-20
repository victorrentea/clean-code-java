package victor.training.fp

import lombok.RequiredArgsConstructor
import lombok.SneakyThrows
import lombok.extern.slf4j.Slf4j
import org.jooq.lambda.Unchecked
import victor.training.fp.model.Order
import java.io.File
import java.io.FileWriter
import java.io.IOException

@Slf4j
data class FileExporter(private val orderRepo: OrderRepo) {
    fun exportOrders() {
        val file = File("target/orders.csv")
        println("Starting export into ${file.absolutePath} ...")
        val t0 = System.currentTimeMillis()
        try {
            FileWriter(file).use { writer ->
                writer.write("order_id;date\n")
                orderRepo.findByActiveTrue()
                    .map { o: Order -> "${o.id};${o.creationDate}\n" }
                    .forEach(Unchecked.consumer { str: String -> writer.write(str) })
                println("Export DONE")
            }
        } catch (e: Exception) {
            println("Export FAILED!" + e) // TERROR-Driven Development
            throw e
        } finally {
            val t1 = System.currentTimeMillis()
            println("Export completed in " + (t1 - t0) / 1000 + " seconds ")
        }
    }
}

@RequiredArgsConstructor
internal class ExportService(private val fileExporter: FileExporter) {
    fun exportOrders() {
        fileExporter.exportOrders()
    }

    fun exportUsers() {
        // TODO implement the export of users using *the same workflow* as for orders
    }
}