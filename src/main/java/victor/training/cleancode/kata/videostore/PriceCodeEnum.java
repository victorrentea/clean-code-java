package victor.training.cleancode.kata.videostore;

public enum PriceCodeEnum {
	 REGULAR (0),
	 NEW_RELEASE (1),
	 CHILDREN (2);

  private final int priceCode;

  PriceCodeEnum(int priceCode){
    this.priceCode = priceCode;
  }
}
