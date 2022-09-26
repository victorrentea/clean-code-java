package victor.training.cleancode

import java.util.*

class ParameterObjects {
    fun placeOrder(fName: String?, lName: String?, city: String, streetName: String, streetNumber: Int) {
        require(!(fName == null || lName == null))
        println("Some Logic")
        println("Shipping to $city on St. $streetName $streetNumber")
        X(1)
    }
}

data class X(val x:Int = 0)


//data class PlaceOrderRequest()
//data class CustomerData()
//data class Address()

fun main() {
    ParameterObjects().placeOrder("John", "Doe", "St. Albergue", "Paris", 99)
}
internal class AnotherClass {
    fun otherMethod(firstName: String?, lastName: String?, x: Int) {
        require(!(firstName == null || lastName == null))
        println("Another distant Logic $x")
        println("Person: $lastName")
    }
}

data class FullName(val firstName: String?, val lastName: String?) {
    init {
        require(!(firstName == null || lastName == null))
    }
}

internal class Person(val fullName: FullName, val phoneNum:String?) {
}
