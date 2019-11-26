package videostore.dirty;

public enum MovieType {
	CHILDREN(2),
	REGULAR(0),
//	BABACIUNI(5),
	NEW_RELEASE(1);

	private final int id;

	MovieType(int id) {
		this.id = id;
	}
}
