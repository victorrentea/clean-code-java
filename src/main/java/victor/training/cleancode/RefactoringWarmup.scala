//package victor.training.cleancode
//
//class RefactoringWarmup {
//  def main(args: Array[String]): Unit = {
//    val two = new Two()
//    println(two.loop(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
//    println(new One(two).f())
//    println(two.g(R(1)))
//  }
//}
//
//class One(private val two: Two) {
//  def f(): Int = {
//    2 * two.g(R(3))
//  }
//}
//
//
//class Two {
//  //  type CustomerId = Int
//  //  type PNK = Int
//  def g(rec: R): Int = {
//    val b = 2
//    println("b2=" + b)
//    println("b=" + b)
//    1 + b + rec.x
//  }
//
//  def loop(numbers: List[Int]): Double = {
//    println("b=" + 42) // sensu' vetii
//    var ssq = 0
//    for (n <- numbers) {
//      if (n % 2 == 0) {
//        ssq += n * n
//      }
//    }
//
//    //    val ssq = numbers.filter(_ % 2 == 0).map(n => n * n).sum
//    // cu for comprehension te rog
//    //    val ssq = for {
//    //      n <- numbers
//    //      if n % 2 == 0
//    //    } yield n * n
//    //    sqrt(ssq)
//  }
//}
//
//case class R(x: Int)
//
//// TODO: Practice Refactoring
////  * How to?
////    - Select text > Right Click > Refactor
////    - Ctrl-Alt-Shift-T/^T to
////    - Keys: [Ctrl-Alt / Opt-Cmd] + [V]ariable/[M]ethod/[P]arameter/i[N]line
////  * What? // after every action undo/revert to start clean
////    - Inline Variable 'b'
////    - Extract Variable '1', '3 * two.g()'
////    - Extract Method 'System.out..' (+replace duplicate)
////    - Inline Method 'g'
////    - Extract Parameter '1', 'r.x()'
////    - Inline Parameter 'c'
////    - Change Signature 'g': add 1 param with default as 1st arg
////    - Extract Interface 'Two'->ITwo; + Inline back to destroy interface
////    - Rename 'g' -> 'h' by Shift-F6 or just edit>Alt-Enter>Rename
////    - Move Method 'g' into R
////    - Preview method/class: Ctrl-Shift-I
////    - Quickfix (Alt/Option -Enter) for->stream
////    - Edit inspection severity & highlighting
////       * Download "aggressive_refactoring.xml" from https://victorrentea.ro
////       and import it in Settings>Editor>Inspections