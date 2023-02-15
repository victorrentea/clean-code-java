package victor.training.cleancode.optional;

public class BadReflection {
  public void method(String countryIso2Code) { // RO, CA, FR, CN
    //    var aClass = BadReflection.class.getClassLoader().getClass(countryIso2Code + "Calculator");
  }
}

class ROCalculator {

}

class CACalculator {

}

class FRCalculator {

}
