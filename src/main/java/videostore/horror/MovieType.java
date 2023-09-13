package videostore.horror;

public enum MovieType {
    CHILDRENS(2),
    REGULAR(0),
    NEW_RELEASE(1);

	public int getCode() {
		return code;
	}

	private final int code;



    MovieType(int code) {
        this.code = code;
    }
}
