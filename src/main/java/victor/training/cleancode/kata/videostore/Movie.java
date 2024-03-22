package victor.training.cleancode.kata.videostore;

import lombok.Getter;

@Getter
public record Movie(String title, Integer priceCode) {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
}
