package victor.training.cleancode

import javax.persistence.Embedded

class ExtractValueObjects {
    // see tests
    fun filterCarModels(criteria: CarSearchCriteria, models: List<CarModel>): List<CarModel> {
        val criteriaInterval = criteria.yearInterval
        val results = models.filter { criteriaInterval.intersects(Interval(it.startYear, it.endYear)) }
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



class CarModel(    val id: Long? = null,
                   val make: String? = null,
                   val model: String? = null,
                   @Embedded
                   val yearInterval: Interval) {

    val startYear: Int
        get() = yearInterval.start
    val endYear: Int
        get() = yearInterval.end

}

class CarModelMapper {
    fun toDto(carModel: CarModel): CarModelDto {
        val dto = CarModelDto()
        dto.make = carModel.make
        dto.model = carModel.model
        dto.startYear = carModel.startYear
        dto.endYear = carModel.endYear
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