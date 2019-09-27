package cleancode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LoosingElementsInSet {
    public static void main(String[] args) {
        Set<Copil> children = new HashSet<>();
        
        Copil childOne = new Copil("Emma");
        children.add(childOne);
        
        System.out.println(childOne.hashCode());
        System.out.println("O am pe Emma? " + children.contains(childOne));

//        childOne.setName("Emma-Simona");
        
        System.out.println(childOne.hashCode());
        System.out.println("O am pe Emma? " + children.contains(childOne));
    }
}

class Copil {
	private final String name; // +0.5%
	public Copil(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copil other = (Copil) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
