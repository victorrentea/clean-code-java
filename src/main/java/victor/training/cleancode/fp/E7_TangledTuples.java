package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RequiredArgsConstructor
public class E7_TangledTuples {
  protected final Api api;

  // ... Reactive Programming
  public Mono<Result> reactiveEnrich(int id) {
    var wtf = api.a(id)
        .flatMap(a -> api.b(a).map(b -> Tuples.of(a, b)))
        .flatMap(t -> api.c(t.getT1(), t.getT2()).map(c -> Tuples.of(t.getT1(), t.getT2(), c)))
        .zipWith(api.d(id), (t, d) -> new Result(t.getT1(), t.getT3(), d));
    return wtf;
  }

  protected interface Api {
    Mono<A> a(int id);
    Mono<B> b(A a);
    Mono<C> c(A a, B b);
    Mono<D> d(int id);
  }

  public record Result(A a, C c, D d) {}

  //region support code
  public record A() {}

  public record B() {}

  public record C() {}

  public record D() {}
  //endregion

}
