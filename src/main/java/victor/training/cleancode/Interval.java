package victor.training.cleancode;

public class Interval {
	private final int start;
	private final int end;
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
}