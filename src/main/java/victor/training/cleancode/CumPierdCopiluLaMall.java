package victor.training.cleancode;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public class CumPierdCopiluLaMall {
  public static void main(String[] args) {
    Set<Element> hashSet = new HashSet<>();
    Element e = new Element().setName("Emma");
    hashSet.add(e);
    System.out.println(hashSet.contains(e)); // true
    // adolescenta
//    e.setName("Emma-Simona");// !a fost implicat in hashCode
    e.setId(42L); // cand faci repo.save => hib seteaza ID
    System.out.println(hashSet.contains(e)); // false
    hashSet.add(e); // 2 WTF?!
    System.out.println("Size:" + hashSet.size()); // false
  }
}

@Data// include @Id generat
class Element {
  @GeneratedValue
  @Id
  Long id;
  String name;
//  List<AltaEntitate> // poate face lazy-load la toString/hash/eq
}