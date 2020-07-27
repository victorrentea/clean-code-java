package victor.training.cleancode;

public class MathUtil {

	public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
	    // http://world.std.com/~swmcd/steven/tech/interval.html
	    return start1 <= end2 && start2 <= end1;
	}
	
}