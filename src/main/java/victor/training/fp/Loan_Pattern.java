package victor.training.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

//   public static <T>  Consumer<T> wrapChecked(ConsumerThrowing<T> myConsumer) {
//      return s -> {
//         try {
//            myConsumer.accept(s);
//         } catch (Exception e) {
//            throw new RuntimeException(e);
//         }
//      };
//   }
interface ConsumerThrowing<T> {
	void accept(T t) throws Exception;
}

@Slf4j
@RequiredArgsConstructor
class FileExporter {


   @FunctionalInterface
   interface ExportContentWriter {
      void writeToFile(Writer writer) throws IOException;
   }
	public void exportFile(ExportContentWriter contentWriter, String fileName) throws IOException {
		File file = new File("target/", fileName);
		log.info("Starting export into {} ...", file.getAbsolutePath());
		long t0 = System.currentTimeMillis();
		try (Writer writer = new FileWriter(file)) {

			contentWriter.writeToFile(writer); // Loan Pattern

			log.info("Export DONE");
		} catch (Exception e) {
			log.error("Export FAILED!"); // TERROR-Driven Development
//         sendErrorEmail(e);
			throw e;
		} finally {
			long t1 = System.currentTimeMillis();
			log.info("Export completed in {} seconds ", (t1 - t0) / 1000);
		}
	}
}

@RequiredArgsConstructor
class ExportFormats {
	private final OrderRepo orderRepo;

	public void writeOrders(Writer writer) {
		try {
			writer.write("order_id;date\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		orderRepo.findByActiveTrue() // WHY?: retrieve and consume, and not collect it in a list. in DB 10M active
										// orders. collect = OOME
				.map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
				.forEach(Unchecked.consumer(writer::write));
	}




	private final UserRepo userRepo;

	public void writeUsers(Writer writer) {
		try {
			writer.write("username;firstname\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		userRepo.findAll().stream()
				.map(u -> u.getUsername() + ";" + u.getFirstName() + "\n")
				.forEach(Unchecked.consumer(s -> writer.write(s)));
	}

}

@RequiredArgsConstructor
class ExportService {
	private final FileExporter fileExporter;
	private final ExportFormats exportFormats;

	@SneakyThrows
	public void exportOrders() {
//      JdbcTemplate jdbc;
//      jdbc.query("sql", (RowMapper<User>) (rs, rowNum) -> rs.getString(1))
		fileExporter.exportFile(exportFormats::writeOrders, "orders.csv");
	}

	@SneakyThrows
	public void exportUsers() {

		fileExporter.exportFile(exportFormats::writeUsers, "users.csv");
		// TODO implement the export of users using *the same workflow* as for orders
	}
}

interface OrderRepo extends JpaRepository<Order, Long> {
	Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
}
