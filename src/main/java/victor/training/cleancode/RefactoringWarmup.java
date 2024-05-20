package victor.training.cleancode;


class PlayGround {
  public static void main(String[] args) {
    System.out.println(new One(new Two()).f());
  }
}

// TODO: Practice Refactor
//  * How?
//    - Select text > Hover
//    - Right-click > Refactor
//    - Ctrl-Alt-Shift-T/^T to
//  * What? /undo after every action to start-over
//    - Inline Variable 'b'
//    - Extract Variable '1', '3 * two.g()'
//    - Extract Method 'System.out..'
//    - Inline Method 'g'
//    - Extract Parameter '1'
//    - Inline Parameter 'c'
//    - Change Signature 'g': add 1 param with default as 1st arg
//    - Extract Interface 'Two'->ITwo; - Inline to Anonymous Class to destroy interface
//    - Rename 'g' -> 'h' by Shift-F6 or just edit>Alt-Enter>Rename

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    return 2 * two.g(3);
  }
}

class Two {
  public int g(int c) {
    int b = 2;
    System.out.println("b=" + b);
    return 1 + b + c;
  }
}
