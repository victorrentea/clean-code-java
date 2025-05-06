package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReactiveCode {

  protected final Api api;

  public Mono<ACD> reactiveEnrich(int id) {
    // blocking⛔️ code:
    var a = api.a(id).block();
    var b = api.b(a).block();
    var c = api.c(a, b).block();
    var d = api.d(id).block();
    return Mono.just(new ACD(a, c, d));

//    var ftw = api.d(id).zipWith(
//        api.a(id)
//            .flatMap(a -> api.b(a)
//                .flatMap(b -> api.c(a, b)
//                    .map(c -> Tuples.of(a, b, c))
//                )));
//    return ftw;
  }

  protected interface Api {
    Mono<A> a(int id);
    Mono<B> b(A a);
    Mono<C> c(A a, B b);
    Mono<D> d(int id);
  }

  //region support code
  public record A() {}

  public record B() {}

  public record C() {}

  public record D() {}

  public record ACD(A a, C c, D d) {}
  //endregion

}
