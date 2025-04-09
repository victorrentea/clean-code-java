package victor.training.cleancode;

import lombok.Setter;
import lombok.experimental.Accessors;

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
    //iexp.d("#situ");
  }
}
@Accessors(fluent = true)
@Setter
class Exp {
  private String a,b,c,d;
  public Exp setA(String a) {
    this.a=a; // muteaza ob curent
    return this; //se returneaza pe sine pt a putea clientul sa puna .b(..).c(..)
  }
}

class IExp { // imutabil
  private final String a;
  private final String b;
  private final String c;
  private final String d;

  IExp(String a, String b, String c, String d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  public static IExpBuilder builder() {
    return new IExpBuilder();
  }

  public static class IExpBuilder {
    private String a;
    private String b;
    private String c;
    private String d;

    IExpBuilder() {
    }

    public IExpBuilder a(String a) {
      this.a = a;
      return this;
    }

    public IExpBuilder b(String b) {
      this.b = b;
      return this;
    }

    public IExpBuilder c(String c) {
      this.c = c;
      return this;
    }

    public IExpBuilder d(String d) {
      this.d = d;
      return this;
    }

    public IExp build() {
      return new IExp(a, b, c, d);
    }

    public String toString() {
      return "IExp.IExpBuilder(a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ")";
    }
  }
}