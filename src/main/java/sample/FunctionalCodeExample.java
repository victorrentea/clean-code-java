package sample;

import io.vavr.control.Try;
import org.jooq.lambda.Unchecked;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalCodeExample {

  private final FooService fooService;
  private final FooDao fooDao;

  private final ExecutorService executorService;

  public FunctionalCodeExample(FooService fooService, FooDao fooDao, ExecutorService executorService) {
    this.fooService = fooService;
    this.fooDao = fooDao;
    this.executorService = executorService;
  }

//                .logFailure() // life with extension function (kt, scala, ...)

  private record X(int a, int b) {
  }

  public void processAll() {

    // this code is imperative in that it does SIDE EFFECTS, not com,puting results.
    // why not List<Try<Foo>>
    // a) there are millions
    // b) a few (files) or huge Foo object !! GB!!
    fooService.fooStream().forEach(this::eat);

//        .flatMap(t -> logAndIgnoreFailure(t, "Streaming foos failed"))
//        // it calls wrap(foo) and if there's an exception -> return a failed Try<>
////        .map(foo -> Try.of(() -> wrap(foo)))
////        .flatMap(t -> logAndIgnoreFailure(t, "Failed wrapping foo"))
//
////        .map(this::wrapSimpler)
////        .flatMap(Optional::stream)
//
//        .flatMap(foo -> wrapSimpler(foo).stream())
//
//        .map(wrapped -> Tuple.of(wrapped, process(wrapped)))
//
//        .filter(tuple -> tuple._2().isFailure())
//        .forEach(tuple -> logError("Couldn't process foo that has id %s".formatted(tuple._1.foo().uuid()), tuple._2.getCause()));
//    Tuple3<Long, String,Map<Long,Tuple2<>>> // = lack of abstractions code smell
  }

  // TODO have a top-level error logger&handler and shortcircuit form every failing point up
  private void eat(Try<Foo> foos) {
    if (foos.isFailure()) {
      logError("Streaming foos failed", foos.getCause());
      return;
    }
    Foo foo = foos.get();
    try {
      FooWrapper w = wrapSimpler(foo);
//    } catch (Exception e) {
//      logError("Failed wrapping foo", e);
//      return;
//    }
//    try {
      process(w);
    } catch (Exception e) {
      logError("Couldn't process foo that has id %s".formatted(foo.uuid()), e.getCause());
    }
  }


  // could be overengineering to pass a function instead of creating 3-4 variants of it
  private <T> Stream<T> streamTryResult(Try<T> t, Consumer<Throwable> failure) {
    return t.map(Stream::of).getOrElseGet(throwable -> {
      failure.accept(throwable);
      return Stream.empty();
    });
  }

  private <T> Stream<T> logAndIgnoreFailure(Try<T> t, String errorLogMessage) {
//        return t.map(Stream::of).getOrElseGet(throwable -> {
//           logError("Streaming foos failed", throwable);
//            return Stream.empty();
//        });
    if (t.isFailure()) {
      logError(errorLogMessage, t.getCause());
      return Stream.empty();
    }
    return Stream.of(t.get());
  }

  private void process(FooWrapper wrapped)  {
//    Future<Integer> f = executorService.submit(() -> 42);
//    Integer i = f.get();
    var foo = wrapped.foo();
    try {

      fooDao.insert(foo);
      Set<Result> results = executorService.invokeAll(toCallable(wrapped)).stream()
          .map(Unchecked.function(Future::get))
          .flatMap(Optional::stream)
          .collect(Collectors.toSet());

      results.forEach(Result::onComplete);

      if (results.stream().allMatch(Result::success)) {
        //other code doing stuff like sending an event
      }
    } catch (Exception e) {
      logError("Couldn't process foo that has id %s".formatted(foo.uuid()), e.getCause());
    }
  }

  private Set<Callable<Optional<Result>>> toCallable(FooWrapper wrapped) {
    return Set.of(()->{
      try {
        // stuff can throw
        return Optional.of(new Result(true, () -> System.out.println("Success"), () -> System.out.println("Failure")));
      } catch (Exception e) {
        logError("bla", e);
        return Optional.empty();
      }
    });
  }

  private FooWrapper wrap(Foo foo) {
    //code
    return new FooWrapper(foo);
  }

  private FooWrapper wrapSimpler(Foo foo) {
    //code
    return new FooWrapper(foo);

  }

  private void logError(String someErrorMessage, Throwable throwable) {

  }


  static class FooService {
    public Stream<Try<Foo>> fooStream() {
//      try (FileInputStream fis = new FileInputStream()) {
//        System.out.println();
//        fis.read();
//      }
      return fooNames().get()
          .stream()
//          .map(name -> readFoos(name).of(is -> {
//            //do some more stuff here
//            return inputStreamToStorage(is).recoverWith(ex -> Try.failure(new RuntimeException("Throw some runtime exception here")));
//            // catch(ex) throw new RuntimeException("Throw some runtime exception here")
//          })
//          .flatMap(Function.identity()));
          .map(this::readFoo);
    }

    private Try<Foo> readFoo(String fileName) {
      try (InputStream is = new FileInputStream(fileName)) {
        return inputStreamToStorage(is);
      } catch (Exception e) {
        return Try.failure(new RuntimeException("Throw some runtime exception here", e));
      }
    }

    private Try<Foo> inputStreamToStorage(InputStream inputStream) {
      //do some stuff and return the result
      return Try.success(new Foo());
    }

    private Try.WithResources1<InputStream> readFoos(String name) {
      return null;
    }

    private Try<Set<String>> fooNames() {
      return null;
    }
  }


  static class Foo {
    List<String> rows;
    public UUID uuid() {
      return UUID.randomUUID();
    }
  }

  //assume this is doing more stuff when it wraps foo - maybe can throw validation exception
  record FooWrapper(Foo foo) {
  }

  static class FooDao {
    public void insert(Foo foo) {

    }
  }

  static class Result {
    private final boolean success;
    private final Runnable runOnSuccess;
    private final Runnable runOnFailure;

    Result(boolean success, Runnable runOnSuccess, Runnable runOnFailure) {
      this.success = success;
      this.runOnSuccess = runOnSuccess;
      this.runOnFailure = runOnFailure;
    }

    public void onComplete() {
      if (success) {
        runOnSuccess.run();
      } else {
        runOnFailure.run();
      }
    }

    public boolean success() {
      return success;
    }
  }
}
