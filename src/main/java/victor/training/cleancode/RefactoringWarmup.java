package victor.training.cleancode;


import lombok.Value;

class PlayGround {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
  }
}

// TODO: Practice Refactoring
//  * How?
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
//    - Rename 'g' -> 'h' by Shift-F6 or just edit>Alt-Enter>Rename
//    - Move Method 'g' into Record

// getteri, constructor, param->campurile finale
record Record(int x) {
  public int g() {
    int b = 2;
    System.out.println("b=" + b);
    return 1 + b + x;
  }
}

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    final Record r = new Record(3);
    return 2 * r.g();
  }
}

class Two {

  public void unknown() {
    System.out.println("a=" + 987);
  }
}
