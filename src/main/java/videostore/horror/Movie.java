package videostore.horror;

import lombok.NonNull;
import lombok.Value;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Value
public class Movie {
	@NonNull
	String title;
	@NonNull
	PriceFactor priceFactor;
}
