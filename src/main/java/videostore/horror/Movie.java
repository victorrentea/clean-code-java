package videostore.horror;

import lombok.RequiredArgsConstructor;

interface SoemStuff {
	public int getPrice(int days);
}

class Me implements SoemStuff {
	@Override
	public int getPrice(int days) {
		return 0;
	}
}


public class Movie {
	@RequiredArgsConstructor
	enum MovieType {
		CHILDRENS, //(d -> formula...),
		REGULAR, // (priceAlgo),
		NEW_RELEASE, // (Me::class),
		ELDERS
		;

//		public final Class<SoemStuff> priceAlgo;


//		public void computePrice() {
//			double thisAmount = 0;
//			switch (this) {
//				case REGULAR:
//					thisAmount += 2;
//					if (dr > 2)
//						thisAmount += (dr - 2) * 1.5;
//					break;
//				case NEW_RELEASE:
//					thisAmount += dr * 3;
//					break;
//				case CHILDRENS:
//					thisAmount += 1.5;
//					if (dr > 3)
//						thisAmount += (dr - 3) * 1.5;
//					break;
//			}
//		}
	}
	private String _title;
	private MovieType _priceCode;

	public Movie(String title, MovieType priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public MovieType getPriceCode() {
		return _priceCode;
	}


	public String getTitle() {
		return _title;
	};
}