package victor.training.cleancode;


import java.util.Arrays;
import java.util.List;

class RefactoringWarmup {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
    loop();
  }

  private static void loop() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    double ssq = numbers.stream().filter(number -> number % 2 == 0).mapToDouble(number -> number * number).sum();
    System.out.println(Math.sqrt(ssq));
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
//    - Extract [V]ariable '1', '3 * two.g()'
//    - Extract [M]ethod 'System.out..'
//    - Inline[N] Method 'g'
//    - Extract [P]arameter '1', 'r.x()'
//    - Inline[N] Parameter 'c'
//    - Change Signature 'g': add 1 param with default as 1st arg
//    - Extract Interface 'Two'->ITwo; - Inline to Anonymous Class to destroy interface
//    - Rename 'g' -> 'h2' by Shift-F6 or just edit>Alt-Enter>Rename
//    - Move Method 'g' into R
//    - Preview method/class: Ctrl-Shift-I
//    - Quickfix for->stream
//    - Change inspection severity & highlighting
//       * Download "aggressive_refactoring.xml" from https://victorrentea.ro
//       and import it in Settings>Editor>Inspections

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    R r = new R(3);
    two.unknown();
    return 2 * r.g();
  }
}

