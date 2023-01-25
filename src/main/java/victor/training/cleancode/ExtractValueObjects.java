package victor.training.cleancode;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
                .filter(model -> criteria.getCriteriaInterval().intersects(model.getYearInterval()))
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }

    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }
}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }
}

class MathUtil {
}
@Embeddable
class Interval { //  = Value Object (imutabil, de obicei mic, si fara PK)
    private int start;
    private int end;

    protected Interval() {} //pt Hibernate

    public Interval(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("start larger than end");
        }
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
        return start <= other.end && other.start <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
    // nu pui setteri ca sa tii obiectul practic IMUTABIL pt developeri
}


class CarSearchCriteria { // smells like JSON ... se mapeaza pe un ecran din FE
    private final int startYear;
    private final int endYear;
//    private final Interval yearInterval;// NU fac asta, pentru ca as strica API modelul - tre sunati frontenzii.
    private final String make;

    public CarSearchCriteria(int startYear, int endYear, String make) {
        this.make = make;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Interval getCriteriaInterval() {
        return new Interval(getStartYear(), getEndYear());
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

@Entity // acel loc unde-ti dai jos bocancii cand intri
class CarModel { // the holy Entity Model
    @Id
    private Long id;
    private String make;
    private String model;
    @Embedded // + 2 coloane in tabela CAR_MODEL
    private Interval yearInterval;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;

        yearInterval = new Interval(startYear, endYear);
    }

    public Interval getYearInterval() {
        return yearInterval;
    }

    public String getMake() {
        return make;
    }

    public Long getId() {
        return id;
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
        dto.startYear = carModel.getYearInterval().getStart();
        dto.endYear = carModel.getYearInterval().getEnd();
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