package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
                .filter(model -> {
                    int start1 = criteria.getStartYear();
                    int end1 = criteria.getEndYear();
                    int start2 = model.getStartYear();
                    int end2 = model.getEndYear();
                    return new Interval(start1, end1).intersectsWith(new Interval(start2, end2));
                })
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }

    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
    }
}
class Alta {
    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
    }

}

class MathUtil {
// de ce sa fac OOP (logica in structuri)? Eu sunt multumit cu structuri anemice @Data si atat,
// si Util-uri, Helpere si Service-uri ?

    // - bun simtz sa ai LOGICA/operatii langa concepte (mai usor pt creier)
    // - Utilu/Helperu e locul in care codul "reusable" se duce sa fie uitat.
    // -   mai usor de gasit metodele
    // - metodele mai scurte si mai simple semnaturi
}

// the missing abstraction
record Interval(int start, int end) {

    public boolean intersectsWith(Interval other) {
        return start <= other.end && other.start <= end;
    }
}

class CarSearchCriteria { // smells like JSON ...
    private final int startYear;
    private final int endYear;
    private final String make;

    public CarSearchCriteria(int startYear, int endYear, String make) {
        this.make = make;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public String getMake() {
        return make;
    }
}

@Entity
class CarModel { // the holy Entity Model
    @Id
    private Long id;
    private String make;
    private String model;
    private int startYear;
    private int endYear;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Long getId() {
        return id;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }


    @Override
    public String toString() {
        return "CarModel{" +
               "make='" + make + '\'' +
               ", model='" + model + '\'' +
               '}';
    }
}


class CarModelMapper {
    public CarModelDto toDto(CarModel carModel) {
        CarModelDto dto = new CarModelDto();
        dto.make = carModel.getMake();
        dto.model = carModel.getModel();
        dto.startYear = carModel.getStartYear();
        dto.endYear = carModel.getEndYear();
        return dto;
    }

    public CarModel fromDto(CarModelDto dto) {
        return new CarModel(dto.make, dto.model, dto.startYear, dto.endYear);
    }
}

class CarModelDto {
    public String make;
    public String model;
    public int startYear;
    public int endYear;
}