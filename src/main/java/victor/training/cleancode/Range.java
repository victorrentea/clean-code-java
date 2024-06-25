package victor.training.cleancode;

public record Range(int start1, int end1) {
    public boolean intervalsIntersect(int start1, int end1) {
        
      return start1 <= end1;
    }
}