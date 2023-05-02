package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
                //                .filter(carModel -> carModel.intersects(criteria))
                .filter(carModel -> MathUtil.intervalsIntersect(
                        criteria.getStartYear(), criteria.getEndYear(),
                        carModel.getStartYear(), carModel.getEndYear()))
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }

    private void applyCapacityFilter() {
        System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
    }

}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
    }

}

class MathUtil {
    public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
        return start1 <= end2 && start2 <= end1;
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

    // SOC: breaking news: Logica in model.
    // 1) Clasa de model nu ar trebui sa se ocupe si de intersects. NU e treaba (resp) ei.
    // responsab unui @Entity e sa descrie obiectul

    // Modelul de Entitati sa NU contina logica de domeniu, ci doar date. (eg @Data)
    // De ce doar date si nu si behavior?
    //  pt ca deja are o rasp: sa descrie structura bazei -> @COlumn @OneToMany
    //
    // daca in entitati las doar date + @ => toata logica va fi in @Service.
    // - Doar ca bucatele mici de logica de domeniu vor putea fi repetate in mai multe locuri. (sunt greu de reused in @SErvice/Util)

    // de ce sa tin @Entity doar cu campuri:
    // - multe campuri eg 40 (!de ce ai asa multe campuri?)
    // - sa nu poluezi modelul cu logica care nu e de business ci de prez/infra


    // 2) E ok sa depinda @Entity CarModel de CarSearchCriteria (DTO=JSON) ? coupling . NU. nu e ok

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