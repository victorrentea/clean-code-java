package victor.training.cleancode;


import java.util.List;

class RefactoringWarmup {
  public static void main(String[] args) {
    Two two = new Two();
    System.out.println(two.loop(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    System.out.println(new One(two).f());
    System.out.println(two.g(new R(1)));
  }
}

class One {
  private final Two two;

  One(Two two) {
    this.two = two;
  }

  public int f() {
    return 2 * two.g(new R(3));
  }
}

class Two {
  public int g(R r) {
    int b = 2;
    System.out.println("b=" + b);
    return 1 + b + r.x();
  }

  public double loop(List<Integer> numbers) {
    System.out.println("b=" + 987);
    // isn't for more efficient? @rui
    // a) numbers.size < 100 - doesn't matter
    // b) numbers.size >100.000 ?? where did these elements come from?
    //    in a well-behaved stateless microservice - NOT from my memory
    //.   orderMonoRepo.streamAll(): Stream<Order>
    //.   orderMonoRepo.search(criteria) : List<Order> // 100.000 elements < <<< WRONG!!
    //.   orderMonoRepo.search(criteria,pageRequest) : Page<Order> // 100 elements x 1000 pages
    // the relative overhead of Stream vs for loop is negligible for BE systems
    double ssq = 0.0;
    for (Integer number : numbers) {
      if (number % 2 == 0) {
        double v = number * number;
        ssq += v;
      }
    }
    //    ssq =  numbers.stream().filter(n->n%2==0).map(n->n*n).reduce(0, Integer::sum);
    ssq = numbers.stream().filter(n -> n % 2 == 0).mapToInt(n -> n * n).sum();
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
