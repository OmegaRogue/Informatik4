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
		Person p = new Person("Mustermann","Max","Brieftraeger",30,"Musterstra�e 10", 9999,"Musterhausen");
		personen.add(p);
		personen.add(new Person("Normal","Otto","Brieftraeger",30,"Musterstra�e 10", 9999,"Musterhausen"));
		
		// Klassisch:
		for(int i = 0;i<personen.size();i++) {
			//System.out.println(i+": " + personen.get(i));
		}
//		Baum meinBaum = new Baum("A");
//		meinBaum.root.addNachfolger("B");
//		meinBaum.root.addNachfolger("C");
//		meinBaum.root.addNachfolger("D");
//		
//		meinBaum.root.nachfolger.get(1).addNachfolger("E");
//		meinBaum.root.nachfolger.get(1).addNachfolger("F");
//		meinBaum.root.rekursiveAusgabe();
//		System.out.println(meinBaum.root.rekursiveSuche("E"));
		Baum deutschland = new Baum("Deutschland");
		deutschland.root.addNachfolger("Baden-Wuertemberg");
		deutschland.root.addNachfolger("Hessen");		
		deutschland.root.addNachfolger("RP");
		deutschland.root.rekursiveAusgabe();	
		deutschland.addNodeAt("Baden-Wuertemberg", "Stuttgart");
		deutschland.root.rekursiveAusgabe();
		
	}

}
