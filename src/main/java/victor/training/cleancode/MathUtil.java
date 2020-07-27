package victor.training.cleancode;

public class MathUtil {

	public static boolean intervalIntersects(Interval interval1, Interval interval2) {
		// http://world.std.com/~swmcd/steven/tech/interval.html
		return interval1.getStart() <= interval2.getEnd() && 
	    		interval2.getStart() <= interval1.getEnd();
	}
	
}