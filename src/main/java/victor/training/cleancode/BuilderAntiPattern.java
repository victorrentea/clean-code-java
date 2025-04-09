package victor.training.cleancode;

import lombok.Builder;
import lombok.Setter;

public class BuilderAntiPattern {
  public static void main(String[] args) {
    Exp exp = new Exp()
        .setA("a")
        .b("b")
        .c("c");
    exp.d("#situ");

    IExp iexp = IExp.builder()
        .a("a")
        .b("b")
        .c("c")
        .build();
//    Exp iexp = new Exp(
//        a="a",
//        b="b",
//        c="c");
    //iexp.d("#situ");
  }
}
//@Accessors(fluent = true)
@Setter
class Exp {
  private String a,b,c,d;
  public Exp setA(String a) {
    this.a=a; // muteaza ob curent
    return this; //se returneaza pe sine pt a putea clientul sa puna .b(..).c(..)
  }
}
@Builder
class IExp { // imutabil
  private final String a,b,c,d;
}
// pe obiectele mutabile nu are sens builder!!

// FOLOSESTE @Builder DOAR DACA ob tau este >3 campuri si immutabil (@Value sau record): eg: Dto, Mondo Document
// FOLOSESTE @Setter fluenti daca ob tau este modificabil: @Entity hibernate
//  Dar: in loc sa repeti @Asccesors(fluent=true) pe fiecare clasa, poti pune "lombok.accessors.fluent=true" in lombok.config in / proiectului
// "builder pattern" 99% din implem este un workaround pt o limitare de limbaj (nu avem in Java named params)