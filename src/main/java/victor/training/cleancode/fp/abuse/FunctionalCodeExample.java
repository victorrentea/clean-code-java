//package victor.training.cleancode.fp.abuse;
//
//import io.vavr.Tuple;
//import io.vavr.control.Try;
//
//import java.io.InputStream;
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class FunctionalCodeExample {
//  private final DataFileService dataFileService;
//  private final DataFileDao dataFileDao;
//  private final ExecutorService executorService;
//
//  public FunctionalCodeExample(DataFileService dataFileService, DataFileDao dataFileDao, ExecutorService executorService) {
//    this.dataFileService = dataFileService;
//    this.dataFileDao = dataFileDao;
//    this.executorService = executorService;
//  }
//
//  public void processAllFiles() {
//    dataFileService.downloadDataFile()
//        .flatMap(t -> streamTryResult(t, throwable -> logError("Download failed", throwable)))
//        .map(dataFile -> Try.of(() -> wrap(dataFile)))
//        .flatMap(t -> streamTryResult(t, ex -> logError("Failed enriching the dataFile", ex)))
//        .map(wrapped -> Tuple.of(wrapped, process(wrapped)))
//        .filter(tuple -> tuple._2().isFailure())
//        .forEach(tuple -> logError("Couldn't process dataFile with id:" + tuple._1.dataFile().uuid(), tuple._2.getCause()));
//  }
//
//  private <T> Stream<T> streamTryResult(Try<T> t, Consumer<Throwable> failure) {
//    return t.map(Stream::of).getOrElseGet(throwable -> {
//      failure.accept(throwable);
//      return Stream.empty();
//    });
//  }
//
//  private Try<UUID> process(final FooWrapper wrapped) {
//    final var dataFile = wrapped.dataFile();
//    return Try.of(() -> {
//      dataFileDao.insert(dataFile);
//      final Set<Result> results = executorService.invokeAll(toCallable(wrapped)).stream()
//          .map(tFuture -> Try.of(tFuture::get).onFailure(throwable -> logError("Couldn't get from future", throwable)).toJavaOptional())
//          .flatMap(Optional::stream)
//          .collect(Collectors.toSet());
//      results.forEach(Result::onComplete);
//      if (results.stream().allMatch(Result::success)) {
//        //other code doing stuff like sending an event
//      }
//      return dataFile.uuid();
//    });
//  }
//
//  private Set<Callable<Result>> toCallable(final FooWrapper wrapped) {
//    // split the work in a set of independent tasks
//    return null;
//  }
//
//  private FooWrapper wrap(DataFile dataFile) {
//    // this does more stuff when it wraps dataFile - maybe can throw validation exception
//    return new FooWrapper(dataFile);
//  }
//
//  private void logError(final String someErrorMessage, final Throwable throwable) {
//  }
//
//  static class DataFileService {
//    /**
//     * @return each DataFile can be up to 1 GB - we need to stream() them
//     */
//    public Stream<Try<DataFile>> downloadDataFile() {
//      return dataFileNames()
//          .get()
//          .stream()
//          .map(name -> readDataFiles(name).of(is -> {
//                // more stuff here
//                return inputStreamToStorage(is).recoverWith(ex -> Try.failure(new RuntimeException("Throw some runtime exception here")));
//              })
//              .flatMap(Function.identity()));
//    }
//
//    private Try<DataFile> inputStreamToStorage(InputStream inputStream) {
//      //do some stuff and return the result
//      return Try.success(new DataFile());
//    }
//
//    private Try.WithResources1<InputStream> readDataFiles(final String name) {
//      return null;
//    }
//
//    private Try<Set<String>> dataFileNames() {
//      return null;
//    }
//  }
//
//  static class DataFile {
//    public UUID uuid() {
//      return null;
//    }
//  }
//
//  record FooWrapper(DataFile dataFile) {
//  }
//
//  static class DataFileDao {
//    public void insert(final DataFile dataFile) {
//    }
//  }
//
//  static class Result {
//    private final boolean success;
//    private final Runnable runOnSuccess;
//    private final Runnable runOnFailure;
//
//    Result(boolean success, Runnable runOnSuccess, Runnable runOnFailure) {
//      this.success = success;
//      this.runOnSuccess = runOnSuccess;
//      this.runOnFailure = runOnFailure;
//    }
//
//    public void onComplete() {
//      if (success) {
//        runOnSuccess.run();
//      } else {
//        runOnFailure.run();
//      }
//    }
//
//    public boolean success() {
//      return success;
//    }
//  }
//}