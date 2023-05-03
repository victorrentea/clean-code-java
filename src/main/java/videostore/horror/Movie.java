package videostore.horror;

import lombok.Value;

@Value
public class Movie {
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	String title;
	int priceCode;

}
