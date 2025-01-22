package victor.training.cleancode.java.kata.funcfusion;

import io.vavr.Tuple;
import io.vavr.control.Try;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentProcessor {
  private final PaymentFileService paymentFileService;
  private final ExecutorService executorService;

  public PaymentProcessor(PaymentFileService paymentFileService, ExecutorService executorService) {
    this.paymentFileService = paymentFileService;
    this.executorService = executorService;
  }

  public void processAllFiles() {
    paymentFileService.streamFiles()
        .flatMap(t -> streamTryResult(t, throwable -> logError("Cannot parse file", throwable)))
        .map(file -> Try.of(() -> loadMetadata(file)))
        .flatMap(fm -> streamTryResult(fm, ex -> logError("Cannot read file metadata", ex)))
        .map(wrapped -> Tuple.of(wrapped, process(wrapped)))
        .filter(tuple -> tuple._2().isFailure())
        .forEach(tuple -> logError("Cannot process: %s".formatted(tuple._1.paymentsFile().fileName()), tuple._2.getCause()));
  }

  private <T> Stream<T> streamTryResult(Try<T> t, Consumer<Throwable> failure) {
    return t.map(Stream::of).getOrElseGet(throwable -> {
      failure.accept(throwable);
      return Stream.empty();
    });
  }

  private Try<String> process(FileWithMetadata wrapped) {
    return Try.of(() -> {
      System.out.println("Insert in DB: " + wrapped);
      Set<Result> results = executorService.invokeAll(toCallable(wrapped))
          .stream()
          .map(tFuture -> Try.of(tFuture::get)
              .onFailure(throwable -> logError("Worker failed", throwable))
              .toJavaOptional())
          .flatMap(Optional::stream)
          .collect(Collectors.toSet());

      results.stream().map(Result::cleanup).forEach(Runnable::run);

      if (results.stream().allMatch(Result::success)) {
        System.out.println("Payment file processed successfully: " + wrapped.paymentsFile().fileName());
      }
      return wrapped.paymentsFile().fileName();
    });
  }

  private Set<Callable<Result>> toCallable(FileWithMetadata wrapped) {
    return null;
  }

  private FileWithMetadata loadMetadata(PaymentsFile paymentsFile) {
    if (paymentsFile.payments().size() == 0) {
      throw new IllegalArgumentException("Empty file");
    }
    if (paymentsFile.payments().size() > 1000) {
      throw new IllegalArgumentException("Too many payments");
    }
    return new FileWithMetadata(paymentsFile, paymentsFile.payments().size());
  }

  record FileWithMetadata(PaymentsFile paymentsFile, int size) {
  }


  private void logError(String someErrorMessage, Throwable throwable) {
    System.out.println(someErrorMessage + ": " + throwable);
  }


  record Result(boolean success, Runnable cleanup) {
  }
}

