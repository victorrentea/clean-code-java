package victor.training.refactoring;

import java.util.List;

import static java.util.Arrays.asList;

public class SeparateQueryCommand {

    // TODO uita-te la cine cheama functia asta si vezi daca nu poti sa-i
    //  faci sa cheme functia getMiscreant nou creeata
    // Daca reusesti sa faci functia asta sa intoarca void, atunci devine command
    public void alertForMiscreant(List<String> people) {
        String miscreant = getMiscreant(people);
        if (!miscreant.isEmpty()) {
            setOffAlarms();
        }
//        return miscreant;
    }


    // pure query function
    public String getMiscreant(List<String> people) {
        return people.stream()
            .filter(person -> asList("Don", "John").contains(person))
            .findFirst()
            .orElse("");
    }

    private void setOffAlarms() {
        System.out.println("Side effects\n");
    }
}
