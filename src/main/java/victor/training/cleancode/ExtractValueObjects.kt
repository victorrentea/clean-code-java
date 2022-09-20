package victor.training.cleancode

import java.util.stream.Collectors

internal class ExtractValueObjects {
    // see tests
    fun filterCarModels(criteria: CarSearchCriteria, models: List<CarModel>): List<CarModel> {
        val results = models
            .filter { model: CarModel ->
                MathUtil.intervalsIntersect(
                    criteria.startYear, criteria.endYear,
                    model.startYear, model.endYear
                )
            }
        println("More filtering logic")
        return results
    }

    private fun applyCapacityFilter() {
        println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000))
    }
}

internal class Alta {
    private fun applyCapacityFilter() {
        println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000))
    }
}

 object MathUtil {
    fun intervalsIntersect(start1: Int, end1: Int, start2: Int, end2: Int): Boolean {
        return start1 <= end2 && start2 <= end1
    }
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
}

//@Entity
 class CarModel {
    // the wholy Entity Model
    //   @Id
    val id: Long? = null
    var make: String? = null
        private set // TODO way of doing in kt?... explore
    var model: String? = null
        private set
    var startYear = 0
        private set
    var endYear = 0
        private set

//    protected constructor() {} // for Hibernate
    constructor(make: String?, model: String?, startYear: Int, endYear: Int) {
        this.make = make
        this.model = model
        require(startYear <= endYear) { "start larger than end" }
        this.startYear = startYear
        this.endYear = endYear
    }

    override fun toString(): String {
        return "CarModel{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}'
    }
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
        return CarModel(dto.make, dto.model, dto.startYear, dto.endYear)
    }
}

 class CarModelDto {
    var make: String? = null
    var model: String? = null
    var startYear = 0
    var endYear = 0
}