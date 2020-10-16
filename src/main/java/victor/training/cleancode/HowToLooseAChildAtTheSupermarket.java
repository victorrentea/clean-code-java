package victor.training.cleancode;

import java.util.HashSet;
import java.util.Set;

public class HowToLooseAChildAtTheSupermarket {
public static void main(String[] args) {
	Set<Child> children = new HashSet<>();
	Child childPoc = new Child("Emma");
	children.add(childPoc);
	
	System.out.println(children.contains(childPoc));
	System.out.println(childPoc.hashCode());
	children.add(childPoc);
	
	// adolescence
//	childPoc.setName("Emma-Simona");
	
	
	System.out.println(childPoc.hashCode());
	System.out.println(children.contains(childPoc));
}
}


class Child {
	private final String name;

//	public void setName(String name) {
//		this.name = name;
//	}
	public Child(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		// NOT MUTABLE fields here !
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) { 
		
		// this CAN include MUTABLE fields
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Child other = (Child) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}