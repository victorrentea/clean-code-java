//package victor.training.fp
//
//import lombok.RequiredArgsConstructor
//import lombok.extern.slf4j.Slf4j
//import victor.training.cleancode.OrderRepo
//import java.io.File
//import java.io.FileWriter
//
//@Slf4j
//class FileExporter {
//    fun exportOrders(writeContent: (FileWriter) -> Unit, fileName: String) {
//        val file = File("target/$fileName")
//        println("Starting export into ${file.absolutePath} ...")
//        val t0 = System.currentTimeMillis()
//        try {
//            FileWriter(file).use { writer ->
//                writeContent(writer)
//                println("Export DONE")
//            }
//        } catch (e: Exception) {
//            println("Export FAILED!" + e) // TERROR-Driven Development
//            throw e
//        } finally {
//            val t1 = System.currentTimeMillis()
//            println("Export completed in " + (t1 - t0) / 1000 + " seconds ")
//        }
//    }
//}
//data class ContentWriters(private val orderRepo: OrderRepo) {
//
//     fun writeOrderContent(writer: FileWriter) {
//        writer.write("order_id;date\n")
//        orderRepo.findByActiveTrue()
//            .map { "${it.id};${it.creationDate}\n" }
//            .forEach { writer.write(it) }
//    }
//     fun writeUserContent(writer: FileWriter) {
//        writer.write("order_id;date\n")
////        orderRepo.findByActiveTrue()
////            .map { "${it.id};${it.creationDate}\n" }
////            .forEach { writer.write(it) }
//    }
//}
//
//@RequiredArgsConstructor
//internal class ExportService(private val fileExporter: FileExporter) {
//    fun exportOrders() {
//        fileExporter.exportOrders({ContentWriters().writeOrderContent(it)}, "orders.csv")
//    }
//
//    fun exportUsers() {
//        fileExporter.exportOrders({ContentWriters().writeUserContent(it)}, "users.csv")
//    }
//}