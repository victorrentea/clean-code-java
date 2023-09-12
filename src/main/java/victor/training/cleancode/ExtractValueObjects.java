package victor.training.cleancode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria,
                                          List<CarModel> models) {
        List<CarModel> results = models.stream()
                .filter(model -> criteria.getYearInterval().intersectsWith(model.getYearInterval()))
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
    // veche naspa

//    private static final BiPredicate<Interval, Interval> yearMatches = (Interval i1, Interval i2) -> {
//
//    }
}
// record Interval(int start, int end) {}  genereaza ce-i mai jos
//@Value //  (lombok)  genereaza ce-i mai jos
// TODO Value Object design pattern =
//  - structura de date imutabila
//  - fara PK (identitate persistenta, lacks continuity of change).
//  - typically small (2-7 campuri)
//  - hash/equals implica toate campurile (nu ca la @Entity)
@Embeddable
class Interval { // DOMAIN OBJECT
    private int start;
    private int end;
    protected Interval() {} // for Hibernate only

    Interval(int start, int end) {
        if (start > end) { // 😱
            throw new IllegalArgumentException("IMPOSIBIL!");
        }
        this.start = start;
        this.end = end;
    }

    // noua buna
    public boolean intersectsWith(Interval other) {
        return start <= other.end && other.start <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return start == interval.start && end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
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

    public Interval getYearInterval() {
        return new Interval(startYear, endYear);
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

    @Embedded
    private Interval yearInterval;  // ii spui lui ORM sa puna campurile Interval in tabela CAR_MODEL (flattening)
//    private int startYear;
//    private int endYear;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        yearInterval = new Interval(startYear, endYear);
    }

    public Interval getYearInterval() {
        return yearInterval;
    }

    public Long getId() {
        return id;
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
        dto.startYear = carModel.getYearInterval().getStart(); // de la asta
        dto.startYear = carModel.getYearInterval().getStart(); // la asta peste tot
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();

        // Law of Demeter: ("regula unui singur .") sa nu traversezi lanturi obiecte
        // pt caller getEndYear e mai scurt si mai decuplat (nu afla de YearInterval)
        // pt structura mea insa, implica boilerplate code (stupid)
//        dto.endYear = carModel.getEndYear(); // NU
        dto.endYear = carModel.getYearInterval().getEnd(); // DA ASA
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