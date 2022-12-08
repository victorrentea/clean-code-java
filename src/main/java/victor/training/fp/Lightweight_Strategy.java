package victor.training.fp;

import java.util.function.Function;

class Movie {
	enum Type {
		REGULAR, NEW_RELEASE, CHILDREN, ELDERS

	}

	private final Type type;

	public Movie(Type type) {
		this.type = type;
	}

	public int computePrice(int days) {
		return switch (type) {
			case REGULAR -> days + 1;
			case NEW_RELEASE -> days * 2;
			case CHILDREN -> 5;
			case ELDERS -> 6;
		};
	}
}

public class Lightweight_Strategy {
	public static void main(String[] args) {
		System.out.println(new Movie(Movie.Type.REGULAR).computePrice(2));
		System.out.println(new Movie(Movie.Type.NEW_RELEASE).computePrice(2));
		System.out.println(new Movie(Movie.Type.CHILDREN).computePrice(2));
	}
}
