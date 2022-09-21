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

internal class Person(firstName: String?, lastName: String?) {
    private val id: Long? = null
    val firstName: String

    // TODO hard-core: implement setter
    var lastName: String
    private val phone: String? = null

    init {
        require(!(firstName == null || lastName == null))
        this.firstName = firstName
        this.lastName = lastName
    }
}

internal class PersonService {
    fun f(person: Person) {
        println("Hi there, " + person.firstName)
        val fullNameStr = person.firstName + " " + person.lastName.uppercase(Locale.getDefault())
        println("Record for $fullNameStr")
    }

    fun p(streetName: String, city: String, streetNumber: Int) {
        println("Living in $city on St. $streetName $streetNumber")
    }

    fun pcaller() {
        p("Dristor", "Bucharest", 91)
    }
}