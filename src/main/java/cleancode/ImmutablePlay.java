package cleancode;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class ImmutablePlay {
	public static void main(String[] args) {
		B b = new B("bb");
		List<String> list = new ArrayList<String>();
		A a = new A("s", b,list);
		
		list.add("Inca una!");
		list.add("Inca doua!");
		
		a.getStrings().add("si p'asta");
		System.out.println(a);
		
		
	}
}
class A {
	private final String s;
	private final B b;
	private final List<String> strings;
	public A(String s, B b, List<String> strings) {
		this.s = s;
		this.b = b;
		this.strings=new ArrayList<>(strings);				
	}
	
	public String getS() {
		return s;
	}
	public List<String> getStrings() {
		return unmodifiableList(strings);
	}

	public String toString() {
		return "A [s=" + s + ", b=" + b + ", strings=" + strings + "]";
	}
	
}
class B {
	public final String x;

	public B(String x) {
		this.x = x;
	}
	
}
