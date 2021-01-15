package victor.training.java8;

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

//interface ExportWriter<T> {
//	String format(T t);
//	String getHeader();
//}
//class User
//class Order

class Exports {
   public static void main(String[] args) {
      Exporter exporter = new Exporter();
      UserContentWriter userContent = new UserContentWriter(null);
      OrderContentWriter orderContent = new OrderContentWriter(null);

      exporter.exportFile("users.csv", userContent::write);
      exporter.exportFile("orders.csv", orderContent::write);
   }
}

//technical stuff, general purpose / almost an Util
class Exporter {
   public File exportFile(String fileName, Consumer<Writer> writeContent) {
      File file = new File("export/" + fileName);
      try (Writer writer = new FileWriter(file)) {
         writeContent.accept(writer);
         return file;
      } catch (IOException e) {
         // insertt a status in the DB
         throw new RuntimeException(e);
      }
   }
}


@Slf4j
@RequiredArgsConstructor
class OrderContentWriter {
   private final OrderRepo repo;

   @SneakyThrows
   public void write(Writer writer)  {
      writer.write("OrderID;Date\n");
      repo.findByActiveTrue()
          .map(o -> o.getId() + ";" + o.getCreationDate())
          .forEach(Unchecked.consumer(writer::write));
      writer.write("Footer");
   }

}

@RequiredArgsConstructor
class UserContentWriter {
   private final UserRepo userRepo;

   @SneakyThrows
   public void write(Writer writer) {
      writer.write("FirstName;LastName\n");
      userRepo.findAll().stream()
          .map(u -> u.getFirstName() + ";" + u.getLastName())
          .forEach(Unchecked.consumer(writer::write));
   }


}

