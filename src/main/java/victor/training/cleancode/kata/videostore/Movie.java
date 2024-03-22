package victor.training.cleancode.kata.videostore;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Movie {
	private final String title;
	private final PriceType priceType;
}