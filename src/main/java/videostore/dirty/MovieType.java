package videostore.dirty;

public enum MovieType {
	CHILDREN(2),
	REGULAR(0),
	NEW_RELEASE(1);

	private final int id;

	MovieType(int id) {
		this.id = id;
	}
}
