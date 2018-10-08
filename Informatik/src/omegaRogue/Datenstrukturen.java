package omegaRogue;
import java.util.ArrayList;
public class Datenstrukturen {

	public static void main(String[] args) {
		/*Liste kursteilnehmer = new Liste("Fabian");
		kursteilnehmer.add("Philipp");
		kursteilnehmer.add("Florian");
		kursteilnehmer.add("Timothy");
		kursteilnehmer.printAll();
		*/
		// Java
		ArrayList<Person> personen = new ArrayList<Person>();
		Person p = new Person("Mustermann","Max","Brieftraeger",30,"Musterstraße 10", 9999,"Musterhausen");
		personen.add(p);
		personen.add(new Person("Normal","Otto","Brieftraeger",30,"Musterstraße 10", 9999,"Musterhausen"));
		
		// Klassisch:
		for(int i = 0;i<personen.size();i++) {
			System.out.println(i+": " + personen.get(i));
		}
	}

}
