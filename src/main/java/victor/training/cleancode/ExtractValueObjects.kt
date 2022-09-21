package victor.training.cleancode

internal class ExtractValueObjects {
    // see tests
    fun filterCarModels(criteria: CarSearchCriteria, carModels: List<CarModel>): List<CarModel> {
        val results = carModels.filter {
            criteria.yearInterval().intersects(it.yearInterval)
        }
        println("More filtering logic")
        return results
    }

    private fun applyCapacityFilter() {
        println(Interval(1000, 1600).intersects(Interval(1250, 2000)))
    }
}

internal class Alta {
    private fun applyCapacityFilter() {
        println(Interval(1000, 1600).intersects(Interval(1250, 2000)))
    }
}

data class Interval(val start:Int, val end:Int) {
    init {
        require(start < end)
    }
    fun intersects(other:Interval) = start <= other.end && other.start <= end
}

class CarSearchCriteria(startYear: Int, endYear: Int, val make: String) {
    // smells like JSON ...
    val startYear: Int
    val endYear: Int

    init {
        require(startYear <= endYear) { "start larger than end" }
        this.startYear = startYear
        this.endYear = endYear
    }
    fun yearInterval() = Interval(startYear, endYear)
}

data class CarModel(
    var make: String? = null,
    var model: String? = null,
    val yearInterval: Interval,
)

class CarModelMapper {
    fun toDto(carModel: CarModel): CarModelDto {
        val dto = CarModelDto()
        dto.make = carModel.make
        dto.model = carModel.model
        dto.startYear = carModel.yearInterval.end
        dto.endYear = carModel.yearInterval.end
        return dto
    }

    fun fromDto(dto: CarModelDto): CarModel {
        return CarModel(dto.make, dto.model, Interval(dto.startYear, dto.endYear))
    }
}

class CarModelDto {
    var make: String? = null
    var model: String? = null
    var startYear = 0
    var endYear = 0
}