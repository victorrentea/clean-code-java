package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
public class Movie {

	private final String title;
	private final PriceCode priceCode;
}
