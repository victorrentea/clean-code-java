@NonNullApi
package victor.training.cleancode.optional.abuse;


import org.springframework.lang.NonNullApi;

// adnotarea asta de pe PACHET ii spune lui Spring ca orice interfata de spring data in acest pachet
// oricand intoarce X (un tip oarecare) dar queryul a gasit nimic,
// sa arunce exceptie in loc sa intoarca NULL.

// adica te va forta sa declari Optional<X> in loc in acea metoda