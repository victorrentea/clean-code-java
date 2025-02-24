package victor.training.cleancode.kata.videostore;

public record Movie( String title, PriceCategory priceCategory )
{
  public enum PriceCategory { CHILDREN,  REGULAR, NEW_RELEASE }
}