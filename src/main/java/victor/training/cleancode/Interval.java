package victor.training.cleancode;

import javax.persistence.Embeddable;

//@AreValorileCOrecte
@Embeddable
public class Interval {
    private int start;
    private int end;
    protected Interval() {} // doar pentru baiatu' de la hibrernate
    public Interval(int start, int end) {
        // obiectele NOI pe care le creezi fa-le by default imutabile + self-validate in ctor
        if (start > end) throw new IllegalArgumentException("Verifica intervalul");
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
       return start <= other.end && other.start <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
