package victor.training.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.pretend.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Stream;

// export all orders to a file

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
	Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}

@Slf4j
@RequiredArgsConstructor
class FileExporter {

	public void export(Consumer<Writer> contentWriter) throws IOException {
		File file = new File("export/orders.csv");
		log.info("Starting export into {} ...", file.getAbsolutePath());
		long t0 = System.currentTimeMillis();
		try (Writer writer = new FileWriter(file)) {
			contentWriter.accept(writer);
			log.info("Export completed in {} seconds ", (System.currentTimeMillis() - t0) / 1000);
		} catch (Exception e) {
			sendErrorEmail(e);
			log.debug("Gotcha!", e); // TERROR-Driven Development
			throw e;
		}
	}
	private void sendErrorEmail(Exception e) {
		// complex code
	}
// pana aici, gunoi de infra
}
@Slf4j
@RequiredArgsConstructor
class OrderExportContentWriter {
	private final OrderRepo orderRepo;

	@SneakyThrows
	public void writeContent(Writer writer) {
		writer.write("OrderID;Date\n");
		orderRepo.findByActiveTrue()
			.map(o -> o.getId() + ";" + o.getCreationDate())
			.forEach(Unchecked.consumer(writer::write));
	}

}
@Slf4j
@RequiredArgsConstructor
class UserExportContentWriter {
	private final UserRepo userRepo;
	@SneakyThrows
	public void writeContent(Writer writer) {
		writer.write("id;last_name\n");
		userRepo.findAll().stream()
			.map(user -> user.getUsername() + ";" + user.getLastName())
			.forEach(Unchecked.consumer(writer::write));
	}
}
@RequiredArgsConstructor
class ExportService {
	private final FileExporter fileExporter;
	private final OrderExportContentWriter orderExportContentWriter;
	private final UserExportContentWriter userExportContentWriter;

	@SneakyThrows
	public void exportOrders() {
		fileExporter.export(orderExportContentWriter::writeContent);
	}
	@SneakyThrows
	public void exportUsers() {
		fileExporter.export(userExportContentWriter::writeContent);
		// TODO
	}
}
