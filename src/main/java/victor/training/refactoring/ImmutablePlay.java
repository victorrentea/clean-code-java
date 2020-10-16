package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.vavr.collection.Stream;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>( Arrays.asList(1, 2, 3, 4, 5));
		Immutable immutable = new Immutable(2, list, new Other(13));
		
		list.add(6);
		
//		Stream.ofAll(immutable.getNumbers()).filte

		//		immutable.getNumbers().add(7);
		immutable.getNumbers().contains(4);
		for(Integer i : immutable.getNumbers()) {
			System.out.println(i);
		}
		
        System.out.println(immutable.getX());
        System.out.println(immutable.getNumbers());
      
    }
}
// final means you are afraid of your colleagues.
class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;
    
    public Immutable(int x, List<Integer> numbers, Other other) {
		this.x = x;
		this.numbers = new ArrayList<>(numbers); // too much for normal cases (?)
		this.other = other;
	}
	public int getX() {
		return x;
	}
//	public List<Integer> getNumbers() {
//		return Collections.unmodifiableList(numbers); // breaks only at runtime
//	}
//	public Iterable<Integer> getNumbers() {
//		return numbers; // only allows for()
//	}
    public List<? extends Integer> getNumbers() {
    	// GEEK ONLY !
		return numbers; // Insane: exposes all the LIst API for READING. but not for updating.
	}
    public Other getOther() {
		return other;
	}
}

class Other {
    private final int a;

    public Other(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

}
