package victor.training.cleancode.kata.videostore;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

//@RequiredArgsConstructor
@Value // =  @Data + private final fields
public class Movie {
	String title;
	PriceType priceType;
}