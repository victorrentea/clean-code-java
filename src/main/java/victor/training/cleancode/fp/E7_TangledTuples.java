package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RequiredArgsConstructor
public class E7_TangledTuples {
  protected final Api api;

  // ... Reactive Programming
  public Mono<Result> reactiveEnrich(int id) {
//    var a = api.a(id).block();
//    var b = api.b(a).block();
//    var c = api.c(a, b).block();
//    var d = api.d(id).block();
//    return Mono.just(new Result(a, c, d));

    var wtf = api.a(id)
        .flatMap(a -> api.b(a)
            .flatMap(b -> api.c(a, b)
                .map(c -> Tuples.of(a, b, c))
            ))
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
