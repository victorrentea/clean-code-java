@NonNullApi // face ca orice interfata de repo de-a ta ce extinde JpaRepository sa NU POATA
    // vreodata sa returneze NULL. In schimb, orice finder pe repo (al tau, cu Query findByName)
// arucna exceptii daca n-a gasit -
// => singurul mod de a returna 'nimic' ramane sa declari Optional<>
package victor.training.cleancode.optional.abuse;

import org.springframework.lang.NonNullApi;