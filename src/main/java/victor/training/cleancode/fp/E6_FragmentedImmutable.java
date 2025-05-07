package victor.training.cleancode.fp;

import lombok.Builder;
import lombok.With;

public class E6_FragmentedImmutable {
  public static void main(String[] args) {
    POI poi = new POI(new Point(50, 50), "drop", "Toilet");
    POI moved = poi.withPoint(jitter(poi));
    System.out.println("jittered poi: " + moved);
  }

  private static Point jitter(POI poi) {
//    return poi.withX(poi.x + 1).withY(poi.y + 1);
//    return poi.toBuilder().x(poi.x()+1).y(poi.y()+1).build();
    return poi.point().moveBy(1, 1);
  }

  record Point(int x, int y) {
    public Point moveBy(int dx, int dy) {
      return new Point(x + dx, y + dy);
    }
  }

  @With
  @Builder(toBuilder = true) // nostalgy for mutable obhjects
  record POI(
      Point point,
      String icon,
      String name
  ) {

//    @NotNull
//    private POI withX(int x) { // withers
//      return new POI(x,y,icon,name);
//    }
  }
}

