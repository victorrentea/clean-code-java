package victor.training.java8;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.pretend.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;



// export all orders to a file

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
	Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
	List<Order> findAllToExport();
}


class StartingPoint {
	FileExporter fileExporter;
	OrderExportWriter orderExportWriter;
	UserExportWriter userExportWriter;

	public void play() throws IOException {
		fileExporter.exportFile("orders.csv", writer -> orderExportWriter.orderWriteContent(writer));
		fileExporter.exportFile("users.csv", userExportWriter::writeUsers);
	}
}


class UserExportWriter {
	private UserRepo userRepo;
	@SneakyThrows
	public void writeUsers(Writer writer) {
		writer.write("username;lastname\n");
		userRepo.findAll()
			.stream()
			.map(user -> user.getUsername() + ";" + user.getLastName())
			.forEach(Unchecked.consumer(writer::write));
	}
}
class OrderExportWriter {
	private OrderRepo repo;
	@SneakyThrows
	public void orderWriteContent(Writer writer) {
		writer.write("OrderID;Date\n");
		repo.findAllToExport()
			.stream()
			.map(o -> o.getId() + ";" + o.getCreationDate()+"\n")
			.forEach(Unchecked.consumer(writer::write));
	}
}

@Slf4j
class FileExporter {

	public File exportFile(String fileName, Consumer<Writer> contentWriterFunc) throws IOException {
		File file = new File("export/" + fileName);
		try (Writer writer = new FileWriter(file)) {
			contentWriterFunc.accept(writer);
			return file;
		} catch (Exception e) {
			// TODO send email notification
			log.debug("Gotcha!", e); // TERROR-Driven Development
			throw e;
		}
	}

}

