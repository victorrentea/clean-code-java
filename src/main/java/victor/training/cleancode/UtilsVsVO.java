package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        //        Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
        results.removeIf(model -> ! 
        		new Interval(model.getStartYear(), model.getEndYear())
        		.intersects(criteria.getYearInterval()));
        System.out.println("More filtering logic");
        return results;
    }
}

class Other {
	private void met() {
		// TODO Auto-generated method stub
		new Interval(1, 3).intersects(new Interval(2, 4));
	}
}

//Ford Focus MK2 car model produced: 2006 - 2010
//search interval: [2008 - 2016]





class CarSearchCriteria {
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

class CarModel {
    private final String make;
    private final String model;
    private final int startYear;
    private final int endYear;

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
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
}