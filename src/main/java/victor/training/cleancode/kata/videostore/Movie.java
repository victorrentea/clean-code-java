package victor.training.cleancode.kata.videostore;

public record Movie( String title, Genre priceCode)
{
  public enum Genre { CHILDREN,  REGULAR, NEW_RELEASE }
}