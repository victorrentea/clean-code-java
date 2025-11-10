package victor.training.cleancode;


import lombok.Data;
import lombok.Value;

import java.util.List;

class RefactoringWarmup {
  public static void main(String[] args) {
    TwoInterface two = new Two();
    System.out.println(two.loop(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    System.out.println(new One(two).f());
    System.out.println(new R(1).g());
  }
}

class TwoBis implements TwoInterface {
  @Override
  public double loop(List<Integer> numbers) {
    return 0;
  }
}
class One {
  private final TwoInterface two;

  One(TwoInterface two) {
    this.two = two;
  }

  public int f() {
    two.loop(List.of());
    return 2 * new R(3).g();
  }
}

class Two implements TwoInterface {
  public static final int BONUS_ANIMAL = 2;

  @Override
  public double loop(List<Integer> numbers) {
    System.out.println("b=" + 987);
    double ssq = 0;
    for (Integer number : numbers) {
      if (number % 2 == 0) {
        ssq += number * number;
      }
    }
    return Math.sqrt(ssq);
  }
}

// = final fields+ctr+getter (fara "get-")+eq/hash+toString
record R(int x) {
  public int g() {
    System.out.println("b=" + Two.BONUS_ANIMAL);
    return 1 + Two.BONUS_ANIMAL + x();
  }
}

// === sau cu lombok, care scrie bytecode la javac
@Data // hint: poti folosi Delombok sa-ti arate codul generat de javac
// interzis pe @Entity de JPA: pui doar @Getter ±@Setter
class RR {
  private final int x;
}

// ===
@Value//❤️
class RRR {
  int x;
}

// TODO: Practice Refactoring
//  * How to?
//    - Select text > Right Click > Refactor
//    - Ctrl-Alt-Shift-T/^T to
//    - Keys: [Ctrl-Alt / Opt-Cmd] + [V]ariable/[M]ethod/[P]arameter/i[N]line
//  * What? // after every action undo/revert to start clean
//    - Inline Variable 'b'
//    - Extract Variable '1', '3 * two.g()'
//    - Extract Method 'System.out..' (+replace duplicate)
//    - Inline Method 'g'
//    - Extract Parameter '1', 'r.x()'
//    - Inline Parameter 'c'
//    - Change Signature 'g': add 1 param with default as 1st arg
//    - Extract Interface 'Two'->ITwo; + Inline back to destroy interface
//    - Rename 'g' -> 'h' by Shift-F6 or just edit>Alt-Enter>Rename
//    - Move Method 'g' into R
//    - Preview method/class: Ctrl-Shift-I
//    - Quickfix (Alt/Option -Enter) for->stream
//    - Edit inspection severity & highlighting
//       * Download "aggressive_refactoring.xml" from https://victorrentea.ro
//       and import it in Settings>Editor>Inspections
