package victor.training.cleancode;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
class RefactoringWarmup {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
    loop();
  }

  private static void loop() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    double ssq = 0.0;
    for (Integer number : numbers) {
      if (number % 2 == 0) {
        double v = number * number;
        ssq += v;
      }
    }
    log.info(String.valueOf(Math.sqrt(ssq)));
  }
}

// TODO: Practice Refactoring
//  * How to?
//    - Select text > Hover
//    - Right-click > Refactor
//    - Ctrl-Alt-Shift-T/^T to
//    - Keys: [Ctrl-Alt / Opt-Cmd] + [V]ariable/[M]ethod/[P]arameter/i[N]line
//  * What? // after every action undo/revert to start clean
//    - Inline[N] Variable 'b'
//    - Extract [V]ariable '1', '3 * two.gg()'
//    - Extract [M]ethod 'System.out..'
//    - Inline[N] Method 'gg'
//    - Extract [P]arameter '1', 'r.x()'
//    - Inline[N] Parameter 'c'
//    - Change Signature 'gg': add 1 param with default as 1st arg
//    - Extract Interface 'Two'->ITwo; - Inline to Anonymous Class to destroy interface
//    - Rename 'gg' -> 'h' by Shift-F6 or just edit>Alt-Enter>Rename
//    - Move Method 'gg' into R
//    - Preview method/class: Ctrl-Shift-I
//    - Quickfix for->stream
//    - Change inspection severity & highlighting
//       * Download "aggressive_refactoring.xml" from https://victorrentea.ro
//       and import it in Settings>Editor>Inspections

record R(int x) {}

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    two.gg(new R(1)); // acest apel vrea 3 in loc de 1 in corpul metodei gg
    return 2 * two.gg(new R(3));
  }
}

class Two {
  public int gg(R naicumsastricinimic) {
    int safe = 2;
    System.out.println("b=" + safe);
    return 1 + safe + naicumsastricinimic.x();
  }

  public void unknown() {
    System.out.println("b=" + 987);
  }
}
