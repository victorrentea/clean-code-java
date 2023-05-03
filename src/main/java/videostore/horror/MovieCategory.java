package videostore.horror;

public enum MovieCategory {
	CHILDREN(2),
	REGULAR(0),
	NEW_RELEASE(1);


	private int index;

	MovieCategory(int index) {

		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
