package victor.training.cleancode.kata.videostore;

public record Movie(String title, Integer priceCode)
{
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;
  public static final int CHILDREN = 2;
}
