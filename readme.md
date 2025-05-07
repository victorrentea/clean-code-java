⭐️ Functional Programming Anti-Patterns

⭐️ by VictorRentea.ro
18y of Java
10y of training🔝 in 150 companies [Hire me!]
100+ talks on YouTube
join victorrentea.ro/community

(no slides on this one)

⭐️ Code: https://github.com/victorrentea/clean-code-java.git
Branch: devoxx-uk-2025

Q: Dark
Q: Copilot ON/OFF? 50%
Disclaimer: not an Ad for IntelliJ (the best IDE on Earth for *any* languge)

⭐️ Agenda:
1️⃣ Mutant Pipeline: side-effects to accumulate data [5m]
using .forEach .ifPresent .peek .map...
2️⃣ Functional Chainsaw: Brain Massacre 🤯 [5m]
findAll().stream() ->> WHERE
->> extract complexity in well-named methods
Hot Curry: return a function is a brain-expensive abstraction <~py,Haskell?
Premature Lazyness: Stream mapped on a result-set blocks a DB connection until consumed
3️⃣ Reduce Abuse <~ TS? [3m]
->> stage-transform the collection
4️⃣ Optional Obsession: overuse inside implementation rather than public APIs [3m]
@Nullable + https://github.com/uber/NullAway [3m]
5️⃣ Monadic Error Mania: leaking to clients <~kt,go? [6m]
~Checked exceptions🤮 ->> fatal exceptions should propagate silently
Useful for bulk processing🤔

?Sealed result types:
sealed class PlaceOrderResult {
data class Success(orderId)
data class PaymentFailed(reason)
object OutOfStock, Error(msg)
}
6️⃣ Fragmented Immutables [7m]
withers / toBuilder ->> semantic methods
changes together ->> new Value Object type
7️⃣ Tangled Tuples <~rx☠️☠️☠️ [7m]
tuples ->> immutable context pattern

