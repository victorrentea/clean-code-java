package victor.training.cleancode

import victor.training.prod.calkt.sample1.Appointment
import javax.persistence.Embedded
import javax.persistence.OneToMany

class ExtractValueObjects {
    // see tests
    fun filterCarModels(criteria: CarSearchCriteria, models: List<CarModel>): List<CarModel> {
        val criteriaInterval = criteria.yearInterval
        val results = models.filter { criteriaInterval.intersects(Interval(it.yearInterval.start, it.yearInterval.end)) }
        println("More filtering logic")
        return results
    }

    private fun applyCapacityFilter() {
        println(Interval(1000, 1600).intersects(Interval(1250, 2000)))
    }
}

class Alta {
    private fun applyCapacityFilter() {
        println(Interval(1000, 1600).intersects(Interval(1250, 2000)))
    }
}

data class Interval(val start:Int, val end:Int) {
    init {
        require(start <= end) { "start larger than end" }
    }
    fun intersects(other: Interval): Boolean {
        return start <= other.end && other.start <= end
    }
}


// smells like JSON ... FROZEN. I can't/ too expensive to change
data class CarSearchCriteria(
    private val startYear: Int,
    private val endYear: Int,
    val make: String) {

    val yearInterval: Interval
        get() = Interval(startYear, endYear)
}

//when(patient.getInsurance()).thenReturn(OMG) // anti-patterbn
//Patient(Insurance(30 attr)) // disgusting iff immutable entities
//Patient(TestData.fullInsurance()) // too much coupling to TestData class
// HORROR QUESTION: isn't the Patient/Insurance class too big?



class CarModel(    val id: Long? = null,
                   val make: String? = null,
                   val model: String? = null,
//                   @OneToMany
//                   val children: List<Appointment>,
                   @Embedded
                   val yearInterval: Interval) {

}

class CarModelMapper {
    fun toDto(carModel: CarModel): CarModelDto {
        val dto = CarModelDto()
        dto.make = carModel.make
        dto.model = carModel.model
        dto.startYear = carModel.yearInterval.start
        dto.endYear = carModel.yearInterval.end
        return dto
    }

    fun fromDto(dto: CarModelDto): CarModel {
        return CarModel(null, dto.make, dto.model, Interval(dto.startYear, dto.endYear))
    }
}

class CarModelDto {
    var make: String? = null
    var model: String? = null
    var startYear = 0
    var endYear = 0
}