package videostore.horror;

//public abstract class Movie { abstract computePrice(days);
//class RegularMovie extends Movie { computePrice(..) {...}
public record Movie(String title, MovieCategory category) {
  // ~@Value dar nativ in cod
}
