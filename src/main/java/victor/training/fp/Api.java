package victor.training.fp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.CompletableFuture;

public class Api {
//    @GetMapping
//    public CompletableFuture<String> method() {
//
//        new AsyncRestTemplate()
//                .exchange()
//                .completable()
//    }

    //1) migrezi si omori nodejs la WebFlux
    //2) omori nodeJS si migrezi proiect la WebFlux (PERICULOS, greu Reactor)
    //3) endpointurile HOT le treci la CompletableFuture( ca mai sus) si omori proxy-ul nodeJS, si pui in loc un reverse proxy de apache
}
