package victor.training.cleancode;


class PlayGround {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
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
//    - Extract [V]ariable '1', '3 * two.g2()'
//    - Extract [M]ethod 'System.out..'
//    - Inline[N] Method 'g2'
//    - Extract [P]arameter '1', 'r.x()'
//    - Inline[N] Parameter 'c'
//    - Change Signature 'g2': add 1 param with default as 1st arg
//    - Extract Interface 'Two'->ITwo; - Inline to Anonymous Class to destroy interface
//    - Rename 'g2' -> 'h' by Shift-F6 or just edit>Alt-Enter>Rename
//    - Move Method 'g2' into R
//    - Preview method/class: Ctrl-Shift-I
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
    two.g2(new R(7)); // UC 323
    return 2 * two.g2(new R(3)); // UC 17
  }
}

class Two {
  public int g2(R r) {
    System.out.println("b=" + 2); // doar pe UC323
    return 1 + 2 + r.x();
  }

  public void unknown() {
    System.out.println("b=" + 987);
  }
}
