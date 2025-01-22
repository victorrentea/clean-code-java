package victor.training.cleancode.java.kata.funcfusion;

import io.vavr.control.Try;

import java.io.*;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentFileService {
  private final String folderPath;

  public PaymentFileService(String folderPath) {
    this.folderPath = folderPath;
  }

  private Try<Set<String>> fileNames() {
    return Try.of(() -> new File(folderPath).listFiles())
        .map(files -> Set.of(files).stream().map(File::getName).collect(Collectors.toSet()));
  }

  public Stream<Try<PaymentsFile>> streamFiles() {
    return fileNames().get().stream()
        .map(name -> openFile(name)
            .of(is -> readFile(name, is)
                .recoverWith(ex -> Try.failure(new RuntimeException("Throw some runtime exception here"))))
            .flatMap(Function.identity()));
  }

  private Try<PaymentsFile> readFile(String fileName, InputStream inputStream) {
    return Try.of (() -> {
      Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
      return new PaymentsFile(fileName, lines.toList());
    });
  }

  private Try.WithResources1<InputStream> openFile(String fileName) {
    return Try.withResources(() -> new FileInputStream(new File(folderPath + "/" + fileName)));
  }
}
