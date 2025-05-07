package victor.training.cleancode.fp;

public class E6_FragmentedImmutable {
  public static void main(String[] args) {
    POI poi = new POI(50, 50, "drop", "Toilet");
    jitter(poi);
    System.out.println("jittered poi: " + poi);
  }

  private static void jitter(POI poi) {
    // TODO +1 to poi.x
    // TODO +1 to poi.y
  }

  record POI(
      int x,
      int y,
      String icon,
      String name
  ) {}
}

