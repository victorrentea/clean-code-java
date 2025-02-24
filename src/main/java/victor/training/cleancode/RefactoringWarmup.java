package victor.training.cleancode;


import java.util.List;

class RefactoringWarmup {
  public static void main(String[] args) {
    Two two = new Two();
    System.out.println(two.loop(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    System.out.println(new One(two).f());
    System.out.println(two.g(3, new R(1)));
  }
}

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    return 2 * two.g(3, new R(3));
  }
}

// Remove all those useless interfaces

// Remove all those useless interfaces !!!
class Two {

  public int g(int i, R r) {
    System.out.println("b=" + i);
    return 1 + i + r.x();
  }

  public double loop(List<Integer> numbers) {
    System.out.println("b=" + 987);
    double ssq = numbers.stream()
        .filter(number -> number % 2 == 0)
        .mapToDouble(number -> number * number)
        .sum();
    return Math.sqrt(ssq);
  }

}

record R(int x) {
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
